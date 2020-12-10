package com.bannad927;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author cbb
 */
@SpringBootApplication
public class ShutDownApplication {

    public static void main(String[] args) {
        /**
         *  method 1:
         * curl -X POST http://localhost:3333/actuator/shutdown
         */
        SpringApplication.run(ShutDownApplication.class, args);

        /**
         *  method 2:
         *  获取程序启动时候的context，然后关闭主程序启动时的context
         *  ctx.close to shutdown all application context
         */
        ConfigurableApplicationContext ctx = SpringApplication.run(ShutDownApplication.class, args);
       /* try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ctx.close();*/

        /**
         * method 3:
         * 在SpringBoot启动的时候将进程号写入一个app.pid文件，生成的路径是可以指定的，
         * 可以通过命令 cat /Users/administrator/app.pid | xargs kill 命令直接停止服务，
         * 这个时候bean对象的PreDestroy方法也会调用的。这种方法大家使用的比较普遍。
         * 写一个start.sh用于启动springboot程序，然后写一个停止程序将服务停止
         */
       /* SpringApplication application = new SpringApplication(ShutDownApplication.class);
        application.addListeners(new ApplicationPidFileWriter("/Users/administrator/app.pid"));
        application.run();*/

        /**
         * method 4:
         * 通过调用一个SpringApplication.exit(）方法也可以退出程序
         */
       // exitApplication(ctx);

        /**
         * method 5:
         * 自己写一个Controller，然后将自己写好的Controller获取到程序的context，然后调用自己配置的Controller方法退出程序
         * curl -X POST http://localhost:3333/shutDownContext
         */

    }

    public static void exitApplication(ConfigurableApplicationContext context) {
        int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);
        System.exit(exitCode);
    }
}
