

返回SqlSessionFactory 其实就是从Configuration 解析
org.apache.ibatis.session.SqlSessionFactoryBuilder.build(java.io.InputStream)
 >org.apache.ibatis.builder.xml.XMLConfigBuilder 构造函数
   >org.apache.ibatis.builder.xml.XMLConfigBuilder.parse
     >org.apache.ibatis.builder.xml.XMLConfigBuilder.parseConfiguration mybatis-config.xml内容
       >org.apache.ibatis.parsing.XPathParser.evaluate
        >org.apache.ibatis.builder.xml.XMLConfigBuilder.mapperElement
         >org.apache.ibatis.session.SqlSessionFactoryBuilder.build(org.apache.ibatis.session.Configuration)
          >org.apache.ibatis.session.defaults.DefaultSqlSessionFactory.DefaultSqlSessionFactory


拿到SqlSession 进行对我们的执行器进行初始化 SimpleExecutor
org.apache.ibatis.session.defaults.DefaultSqlSessionFactory.openSession()
 >org.apache.ibatis.session.defaults.DefaultSqlSessionFactory.openSessionFromDataSource
  >org.apache.ibatis.transaction.TransactionFactory.newTransaction(javax.sql.DataSource, org.apache.ibatis.session.TransactionIsolationLevel, boolean)
    >org.apache.ibatis.session.Configuration.newExecutor(org.apache.ibatis.transaction.Transaction, org.apache.ibatis.session.ExecutorType)
      >org.apache.ibatis.executor.SimpleExecutor
        >org.apache.ibatis.executor.CachingExecutor  一级缓存 自动
          >org.apache.ibatis.plugin.InterceptorChain.pluginAll 责任链模式拦截器


操作数据库
org.apache.ibatis.session.defaults.DefaultSqlSession.selectOne(java.lang.String, java.lang.Object)
 >org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(java.lang.String, java.lang.Object)
   >org.apache.ibatis.session.Configuration.getMappedStatement(java.lang.String)
    >org.apache.ibatis.executor.CachingExecutor.query(org.apache.ibatis.mapping.MappedStatement, java.lang.Object, org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler)
     >org.apache.ibatis.executor.CachingExecutor.createCacheKey 缓存的key
      >org.apache.ibatis.executor.CachingExecutor.query(org.apache.ibatis.mapping.MappedStatement, java.lang.Object, org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler, org.apache.ibatis.cache.CacheKey, org.apache.ibatis.mapping.BoundSql)
         >org.apache.ibatis.executor.BaseExecutor.queryFromDatabase
           >org.apache.ibatis.executor.BaseExecutor.doQuery
             >org.apache.ibatis.executor.statement.PreparedStatementHandler.query
               >org.apache.ibatis.executor.resultset.ResultSetHandler.handleResultSets
                >org.apache.ibatis.executor.resultset.DefaultResultSetHandler
               >

     cache key: id +sql+limit+offsetxxx


获取mapper：
org.apache.ibatis.session.defaults.DefaultSqlSession.getMapper
   >org.apache.ibatis.session.Configuration.getMapper
    >org.apache.ibatis.binding.MapperRegistry.getMapper
      >org.apache.ibatis.binding.MapperProxyFactory.newInstance(org.apache.ibatis.session.SqlSession)
        >org.apache.ibatis.binding.MapperMethod.execute

sql:
  org.apache.ibatis.binding.MapperMethod.execute
    >org.apache.ibatis.session.SqlSession.selectOne(java.lang.String, java.lang.Object)
      >org.apache.ibatis.executor.BaseExecutor.query(org.apache.ibatis.mapping.MappedStatement, java.lang.Object, org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler)
        >org.apache.ibatis.mapping.MappedStatement.getBoundSql
