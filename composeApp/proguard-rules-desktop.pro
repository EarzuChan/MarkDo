# 解决 jsoup 找不到 re2j 的报错
-dontwarn com.google.re2j.**

# 解决 JNA 的警告
-dontwarn com.sun.jna.**

# 解决 SLF4J 警告
-dontwarn org.slf4j.impl.**

# 常用：保留 Compose Desktop 运行时必需的元数据
-keepattributes *Annotation*, Signature, InnerClasses, EnclosingMethod