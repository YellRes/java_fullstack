# 00 · 工具链决策（2026-08-12 定）

## 结论表

| 要装 | 选择 | 为什么 |
|---|---|---|
| JDK | **Temurin 21**（LTS） | 21 资料最全。不选最新版——学习期别给自己加"版本太新没人遇到过"的难度 |
| 构建工具 | **不装 Maven**，用项目自带 `mvnw` | Maven Wrapper 会自己下对应版本。同 `npx` / `packageManager` 思路：版本跟着项目走 |
| 编辑器 | **第一天 VS Code，第 3 周切 IDEA** | 见下面对比 |
| 数据库 | **Docker 起 MySQL**（阶段 1 再动） | 已有 Docker。一行 `docker run`，用完删掉不脏机器 |
| 接口测试 | 第一天浏览器就够（GET） | POST 等阶段 0 后半段，用 VS Code 的 REST Client 扩展或 Apifox |

安装命令：

```
winget install EclipseAdoptium.Temurin.21.JDK
```

装完**必须新开终端**才能刷新 PATH。ID 找不到就 `winget search temurin`。

---

## VS Code vs IDEA：真实差距

| | VS Code | IDEA |
|---|---|---|
| **我的上手成本** | 0，已经会 | 键位、Project/Module/SDK 模型都要重学 |
| **前后端同窗口** | 一个 workspace 开两个文件夹，Vue + Java 一起改 | Ultimate 可以，Community 前端支持很弱 |
| 基础写码/补全/跳转 | 够用 | 更快，但不是决定性差距 |
| **重构** | 基础可用，明显弱一截 | 行业标杆。Java 是重构密集语言 |
| **调试** | 打断点看变量够用 | 条件断点、表达式求值、drop frame |
| **MyBatis Mapper ↔ XML 跳转** | 没有好的等价物 | MybatisX 插件一键双向跳，**阶段 1 起天天用** |
| **内嵌数据库工具** | 要另开 Navicat | Ultimate 自带（就是 DataGrip），SQL 有表名字段名补全 |
| **Spring 容器可视化** | 无 | Bean 依赖图、`@Autowired` 跳实现、循环依赖检测 |
| 内存占用 | 小 | 大 |

### 为什么切换点定在第 3 周

上面这张表里，**对"学 Spring"影响最大的是最后一项**，不是重构或调试。

Spring 的核心难点是它把控制流藏起来了——看不见谁创建了 Bean、`@Autowired` 注入的到底是哪个实现、`@Transactional` 有没有真生效。所谓"Spring 魔法"之所以难，就是因为**它是不可见的**。IDEA 的 Bean 导航能把这层不可见的容器变成能点开跳转的东西。

所以它是**学习期的脚手架**，不是长期依赖——等心智模型建立好了，这个功能反而没那么重要了。

第 3 周（阶段 1，开始写 MyBatis + 多表查询）正是这三项能力开始起作用的时间点，也是切换成本最低的时间点：那时项目还小，习惯还没固化。

### License 的时间安排

- **先问公司有没有 JetBrains 授权**——前端团队常买 WebStorm，而 All Products Pack 包含 IDEA Ultimate。
- 如果没有：**别现在激活 Ultimate 的 30 天试用**。现在激活会在阶段 1 中途到期，那正是最需要它的时候。留到第 3 周激活，30 天正好覆盖阶段 1。

---

## VS Code 配 Java

装两个扩展包（都是官方维护）：

```
Extension Pack for Java        (Microsoft)
Spring Boot Extension Pack     (VMware / Broadcom)
```

前者给语言支持 + 调试 + 测试 + Maven 集成；后者给 `application.yml` 补全、Spring Boot Dashboard（可视化启停、看 endpoint 列表）。

### 三个使用要点

1. **打开的必须是含 `pom.xml` 的那层目录**（`D:\java\adboard`），不是 `src`。Java 扩展靠 `pom.xml` 识别项目。
2. **第一次打开等 "Importing Maven project..." 跑完再动手。** 没等完就编辑会满屏红波浪线——那是假报错。
3. **收藏这个命令**：代码明显没错但满屏红波浪线 → `Ctrl+Shift+P` → `Java: Clean Java Language Server Workspace` → 重启。这是 VS Code 写 Java 最常见的一类故障，提前知道能省一小时慌乱。

启动方式三选一：主类上方 `Run` 小箭头、Spring Boot Dashboard 启动按钮、终端 `./mvnw spring-boot:run`。

---

## 技术栈全景（阶段 1–3 会陆续用到）

**要学**：Java 21 · Spring Boot 3 · MyBatis-Plus（**不用 JPA**）· MySQL 8（重点）· Redis · Docker · JUnit 5 + MockMvc · Lombok

**先不学**：Spring Cloud 全家桶 · Kafka 深入 · Spring Security（手写 JWT 拦截器代替）· JVM 调优参数 · 分布式事务 · Netty

> 为什么不用 JPA/Hibernate：国内用得少，且它把 SQL 藏起来了——正好挡住我最该练的那块能力（第 4 节说的四大鸿沟之一）。
