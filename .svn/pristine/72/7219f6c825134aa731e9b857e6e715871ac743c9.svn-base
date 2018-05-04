package com.lingnet.hcm.action.check;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.lingnet.util.ToolUtil;
/**
 * 
 * @ClassName: MyJob 
 * @Description: 定时任务控制层 
 * @author wangqiang
 * @date 2017年4月13日 下午3:41:01 
 *
 */
public class MyJob extends QuartzJobBean{
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		//定时执行的方法
		String pathUrl = ToolUtil.getPropert("config.properties", "sync_path");
		HttpPost httpPost = new HttpPost(pathUrl);//post提交信息
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		System.out.println("executing request:" + httpPost.getURI());
		HttpResponse resp = null;
		try {
			resp = client.execute(httpPost);
			if(resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity he = resp.getEntity();
				respContent = EntityUtils.toString(he,"UTF-8");
				if (!"success".equals(respContent)){//若是不成功
					throw new Exception();
	        	}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
