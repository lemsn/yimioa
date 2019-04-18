package com.redmoon.oa.message;

import java.lang.reflect.*;

import com.cloudwebsoft.framework.aop.advice.*;
import com.redmoon.oa.sms.SMSFactory;
import com.redmoon.oa.sms.IMsgUtil;
import org.apache.log4j.Logger;
import com.redmoon.oa.person.UserMgr;
import com.redmoon.oa.person.UserDb;
import cn.js.fan.util.StrUtil;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class MobileAfterAdvice extends AfterAdvice {

    /**
     * Before
     *
     * @param proxy Object
     * @param method Method
     * @param args Object[]
     * @throws Throwable
     * @todo Implement this com.cloudwebsoft.framework.aop.base.Advice method
     */
    public void After(Object proxy, Method method, Object[] args) throws
            Throwable {
        if (method.getName().equals("AddMsg")) {
            // System.out.println("proxy obj=" + proxy);
            IMessage imsg = (IMessage) proxy;
            String receiver = imsg.getFileUpload().getFieldValue("receiver");
            // System.out.println(this.getClass().getName() + " receiver=" + imsg.getFileUpload().getFieldValue("receiver"));
            // System.out.println(this.getClass().getName() + " toUser=" + imsg.getReceiver());
            IMsgUtil imu = SMSFactory.getMsgUtil();
            String isToMobile = StrUtil.getNullStr(imsg.getFileUpload().getFieldValue("isToMobile"));
            if (isToMobile.equals("true") && imu != null) {
                String[] ary = receiver.split(",");
                int len = ary.length;
                UserMgr um = new UserMgr();
                UserDb ud = null;
                for (int i = 0; i < len; i++) {
                    ud = um.getUserDb(ary[i]);
                    imu.send(ud, imsg.getContent(), imsg.getSender());
                }
            }
        }
        else if (method.getName().equals("sendSysMsg")) {
            IMsgUtil imu = SMSFactory.getMsgUtil();
            if (imu != null) {
                UserMgr um = new UserMgr();
                UserDb ud = um.getUserDb((String) args[0]);
                imu.send(ud, (String) args[2], MessageDb.SENDER_SYSTEM);
            }
        }
        else if (method.getName().equals("TransmitMsg")) {
            IMessage imsg = (IMessage) proxy;
            
            String receiver = imsg.getReceiver();
                        
            // String receiver = imsg.getFileUpload().getFieldValue("receiver");
            // System.out.println(this.getClass().getName() + " receiver=" + imsg.getFileUpload().getFieldValue("receiver"));
            // System.out.println(this.getClass().getName() + " toUser=" + imsg.getReceiver());
            IMsgUtil imu = SMSFactory.getMsgUtil();
            // String isToMobile = StrUtil.getNullStr(imsg.getFileUpload().getFieldValue("isToMobile"));
            
            // System.out.println(getClass() + " receiver=" + receiver + " imu=" + imu);
            
            if (imu != null) {
                String[] ary = receiver.split(",");
                int len = ary.length;
                UserMgr um = new UserMgr();
                UserDb ud = null;
                for (int i = 0; i < len; i++) {
                    ud = um.getUserDb(ary[i]);
                    imu.send(ud, imsg.getContent(), imsg.getSender());
                }
            }        	
        }        
    }
}