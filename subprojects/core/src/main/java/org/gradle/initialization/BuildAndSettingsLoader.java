/*
 * Copyright 2017 the original author or authors.
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

package org.gradle.initialization;

import org.gradle.api.internal.GradleInternal;
import org.gradle.api.internal.SettingsInternal;

public class BuildAndSettingsLoader implements SettingsLoader {
    private final SettingsLoader settingsLoader;
    private final BuildLoader buildLoader;

    public BuildAndSettingsLoader(SettingsLoader settingsLoader, BuildLoader buildLoader) {
        this.settingsLoader = settingsLoader;
        this.buildLoader = buildLoader;
    }

    @Override
    public SettingsInternal findAndLoadSettings(final GradleInternal gradle) {
        SettingsInternal settings = settingsLoader.findAndLoadSettings(gradle);
        buildLoader.load(settings.getRootProject(), settings.getDefaultProject(), gradle, settings.getRootClassLoaderScope());
        return settings;
    }
}