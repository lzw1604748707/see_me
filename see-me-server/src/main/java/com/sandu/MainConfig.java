package com.sandu;

import com.alibaba.druid.filter.logging.Log4jFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log4jLogFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;
import com.sandu.common.model._MappingKit;
import com.sandu.common.routes.AdminRoutes;

public class MainConfig extends JFinalConfig {

	private static Prop p = loadConfig();

	private static Prop loadConfig() {
		return PropKit.use("dev_config.txt").appendIfExists("config.txt");
	}

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(p.getBoolean("devMode"));
		me.setMaxPostSize(1024 * 1024 * 20);
		me.setLogFactory(new Log4jLogFactory());
		me.setBaseUploadPath("upload/temp");
		me.setError404View("/admin_index.html");
		me.setJsonDatePattern("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public void configRoute(Routes me) {
		me.add(new AdminRoutes());
	}

	@Override
	public void configPlugin(Plugins me) {

		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
		// orm映射 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setShowSql(p.getBoolean("devMode"));
		arp.setDialect(new MysqlDialect());
		arp.setBaseSqlTemplatePath(PathKit.getRootClassPath());
		arp.addSqlTemplate("jxt.sql");
		arp.getSqlKit().getEngine().addSharedObject("sk", new com.jfinal.kit.StrKit());

		// 配置log插件
		Log4jFilter logFilter = new Log4jFilter();
		logFilter.setStatementLogEnabled(false);
		logFilter.setStatementLogErrorEnabled(true);
		logFilter.setStatementExecutableSqlLogEnable(true);
		druidPlugin.addFilter(logFilter);

		_MappingKit.mapping(arp);
		// 添加到插件列表中
		me.add(druidPlugin);
		// 缓存
		me.add(new EhCachePlugin());
		// ORM
		me.add(arp);
	}

	@Override
	public void configInterceptor(Interceptors me) {
	}

	@Override
	public void configHandler(Handlers me) {
	}

	@Override
	public void configEngine(Engine me) {
		me.setDevMode(p.getBoolean("devMode"));
		me.addSharedObject("sk", new com.jfinal.kit.StrKit());
	}

	@Override
	public void afterJFinalStart() {

	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/");
	}

}
