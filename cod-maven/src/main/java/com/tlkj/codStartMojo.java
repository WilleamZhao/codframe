package com.tlkj;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.tlkj.cod.core.launcher.CodLauncher;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * 启动框架插件
 *
 * @author sourcod
 */
@Mojo(name = "run", requiresProject = false, defaultPhase = LifecyclePhase.PACKAGE)
public class codStartMojo extends AbstractMojo {

    @Parameter(property = "maven.facelog.fork", defaultValue = "false")
    private boolean fork;

    @Override
    public void execute() {
        CodLauncher.main(null);
        System.out.println(fork);
        if (!fork) {
            waitIndefinitely();
        }
    }

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
