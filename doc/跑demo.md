1. 解压 seata-server-0.5.0.rar 启动 .\seata-server-0.5.0\bin\seata-server.bat
2. 执行sql
3. 启动各个子服务
4. BusinessController 调用
5. 测试
   - http://localhost:25001/purchase/commit
   - http://localhost:25001/purchase/rollback
   - http://localhost:25001/purchase/noTransaction

