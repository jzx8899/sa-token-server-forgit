package com.yk.sso;

import cn.dev33.satoken.config.SaSsoConfig;
import cn.dev33.satoken.sso.SaSsoProcessor;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.yk.bean.TbUser;
import com.yk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Sa-Token-SSO Server端 Controller 
 * @author kong
 *
 */
@RestController
public class SsoServerController {
	@Autowired
	private UserService userService;

	/*
	 * SSO-Server端：处理所有SSO相关请求 
	 * 		http://{host}:{port}/sso/auth			-- 单点登录授权地址，接受参数：redirect=授权重定向地址 
	 * 		http://{host}:{port}/sso/doLogin		-- 账号密码登录接口，接受参数：name、pwd 
	 * 		http://{host}:{port}/sso/checkTicket	-- Ticket校验接口（isHttp=true时打开），接受参数：ticket=ticket码、ssoLogoutCall=单点注销回调地址 [可选] 
	 * 		http://{host}:{port}/sso/signout		-- 单点注销地址（isSlo=true时打开），接受参数：loginId=账号id、secretkey=接口调用秘钥 
	 */
	@RequestMapping("/sso/*")
	public Object ssoRequest() {
		return SaSsoProcessor.instance.serverDister();
	}

	// 配置SSO相关参数 
	@Autowired
	private void configSso(SaSsoConfig sso) {
		
		// 配置：未登录时返回的View 
		sso.setNotLoginView(() -> {
			String msg = "当前会话在SSO-Server端尚未登录，请先访问"
					+ "<a href='/sso/doLogin?name=aaa&pwd=123' target='_blank'> doLogin登录 </a>"
					+ "进行登录之后，刷新页面开始授权";
			return msg;
		});
		
		// 配置：登录处理函数 
		sso.setDoLoginHandle((name, pwd) -> {
			// 此处仅做模拟登录，真实环境应该查询数据进行登录
			List<TbUser> tbUserList = userService.queryByName(name);
			System.out.println("tbUserList = " + tbUserList);
			for (TbUser tbUser : tbUserList) {
				if(tbUser.getName().equals(name) && tbUser.getPassword().equals(pwd)) {
					StpUtil.login(tbUser.getId());
					return SaResult.ok("登录成功！").setData(StpUtil.getTokenValue());
				}
			}
			return SaResult.error("登录失败！");
		});
		
/*		// 配置 Http 请求处理器 （在模式三的单点注销功能下用到，如不需要可以注释掉）
		sso.setSendHttp(url -> {
			try {
				// 发起 http 请求 
				System.out.println("------ 发起请求：" + url);
				return Forest.get(url).executeAsString();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		});*/
	}
	
}
