# 构建问题修复总结 (Build Troubleshooting Summary)

本文档记录了在修复 `samples/testapp` 编译过程中遇到的关键问题及其解决方案。

## 1. 代码生成缺失问题 (KSP/CBOR Serialization)

### 问题现象
编译报错 `Unresolved reference 'toDataItem'` 或 `Unresolved reference 'fromDataItem'`，通常出现在使用了 `@CborSerializable` 注解的数据类（如 `ShowResponseMetadata`）。

### 原因分析
在 Kotlin Multiplatform (KMP) 项目中，KSP (Kotlin Symbol Processing) 如果配置不当，可能导致：
1. 注解处理器未在特定目标（如 Android）上运行。
2. 生成的代码路径未被添加到 SourceSet 中。
3. IDE 无法识别生成的符号。

### 解决方案：手动实现序列化
用手动编写的扩展方法替代自动生成。

**代码示例**：
```kotlin
// 移除 @CborSerializable 注解
data class MyData(val field: String) {
    // 手动实现转 CBOR
    fun toDataItem(): DataItem {
        return buildCborMap {
            put("field", field)
        }
    }

    // 手动实现从 CBOR 解析
    companion object {
        fun fromDataItem(dataItem: DataItem): MyData {
            require(dataItem is CborMap)
            return MyData(
                field = (dataItem["field"] as Tstr).value
            )
        }
    }
}
```

**评价**：
- **稳妥性**：高。消除了对构建工具链的依赖。
- **维护性**：中。新增字段需手动更新代码。

---

## 2. iOS 环境干扰 Android 构建

### 问题现象
在只编译 Android APK (`assembleBlueDebug`) 时，Gradle 报错 `xcode-select: error` 或试图运行 `xcodebuild`。

### 原因分析
原项目的 `build.gradle.kts` 中使用 `if (HostManager.hostIsMac)` 自动开启 iOS 构建任务。只要在 Mac 上，就会触发 iOS 相关的 cinterop 和 SwiftBridge 编译，即使只需要 Android 包。

### 解决方案：引入条件编译开关
修改 `build.gradle.kts`，引入 `buildIos` 属性控制。

1. **定义属性**：
   ```kotlin
   val buildIos = project.findProperty("buildIos") == "true"
   ```

2. **包裹配置**：
   将所有 iOS 相关的 `targets`, `sourceSets`, 和 `tasks` 依赖都包裹在 `if (buildIos) { ... }` 中。

3. **使用方式**：
   - 仅 Android: `./gradlew assembleDebug`
   - 含 iOS: `./gradlew assembleDebug -PbuildIos=true`

---

## 3. Git 命令与 Xcode 许可问题

### 问题现象
构建失败，提示 `Process 'command 'git'' finished with non-zero exit value 69` 或 `You have not agreed to the Xcode license agreements`。

### 原因分析
根目录的 `build.gradle.kts` 试图运行 `git rev-list` 来生成版本号。在 macOS 上，如果没有同意 Xcode 许可，系统自带的 git 命令会被拦截。

### 解决方案：临时硬编码版本
在 `build.gradle.kts` 中暂时注释掉 git 命令执行，直接返回固定版本号。

```kotlin
val projectVersionCode: Int by extra {
    1 // Hardcoded
}

val projectVersionName: String by extra {
    "0.95.0-debug" // Hardcoded
}
```

---

## 4. Gradle 任务依赖安全性

### 问题现象
报错 `Task with name 'xxx' not found`。

### 原因分析
直接调用 `dependsOn("taskName")` 时，如果该任务因为条件配置（如上述的 iOS 开关）而未创建，构建会失败。

### 解决方案
使用安全查找方式：
```kotlin
val task = tasks.findByName("someTask")
if (task != null) {
    dependsOn(task)
}
```
