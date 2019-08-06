package com.tlkj.cod.boot;

import com.tlkj.cod.core.launcher.CodLauncher;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Desc maven 启动插件
 *
 * @author sourcod
 * @version 1.0
 * @className CodBootMavenMojo
 * @date 2019/8/6 2:01 AM
 */
@Mojo(name = "codBootRun", requiresProject = false)
public class CodBootMavenMojo extends AbstractMojo {

    //@Parameter(property = "maven.codframe.fork", defaultValue = "false")
    //private boolean fork;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        CodLauncher.main(null);
        /*if (!fork) {
            waitIndefinitely();
        }*/
    }

    /**
     * Causes the current thread to wait indefinitely. This method does not
     * return.
     */
    private void waitIndefinitely() {
        Object lock = new Object();

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                getLog().warn("RunMojo.interrupted", e);
            }
        }
    }
}
