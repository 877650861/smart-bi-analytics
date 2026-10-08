# 智能数据分析与运营决策平台

面向运营人员的自助分析后端：用户上传 Excel / CSV 数据并提交分析目标，服务将图表任务异步投递到 RabbitMQ，调用模型生成 ECharts 配置和结论后回写任务状态，避免请求长时间阻塞并支持失败重试与死信处理。

## 技术栈

Java 8、Spring Boot 2.7、MyBatis-Plus、MySQL、Redis、RabbitMQ、线程池、EasyExcel、AIGC。

## 本地运行

1. 创建 MySQL 数据库并执行 `sql/create_table.sql`。
2. 准备 MySQL、Redis、RabbitMQ，并通过环境变量设置连接信息。
3. 配置 `XUNFEI_APP_ID`、`XUNFEI_API_SECRET`、`XUNFEI_API_KEY` 或替换为自己的模型适配实现。
4. 运行 `mvn -DskipTests package`，再执行 `java -jar target/yubi-backend-0.0.1-SNAPSHOT.jar`。

接口前缀为 `/api`，容器健康检查为 `GET /api/health`。

## Railway 环境变量

`SPRING_DATASOURCE_URL`、`SPRING_DATASOURCE_USERNAME`、`SPRING_DATASOURCE_PASSWORD`、`SPRING_REDIS_HOST`、`SPRING_REDIS_PORT`、`SPRING_REDIS_PASSWORD`、`SPRING_RABBITMQ_HOST`、`SPRING_RABBITMQ_PORT`、`SPRING_RABBITMQ_USERNAME`、`SPRING_RABBITMQ_PASSWORD`，以及模型服务所需的 `XUNFEI_*` 变量。

## 来源与改造说明

本仓库基于网盘中已获授权的“智能 BI 平台”后端源码整理，保留原项目的任务、消息队列和图表生成实现；本仓库新增了环境变量化配置、容器健康检查和 Railway 部署描述。原项目作者与教学来源：程序员鱼皮 / 编程导航。
