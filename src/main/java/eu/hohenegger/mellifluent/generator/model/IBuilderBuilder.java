/*-
 * #%L
 * mellifluent-core
 * %%
 * Copyright (C) 2020 - 2021 Max Hohenegger <mellifluent@hohenegger.eu>
 * %%
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
 * #L%
 */
package eu.hohenegger.mellifluent.generator.model;

import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;

public interface IBuilderBuilder {

    static final String GENERATED_BY = "GENERATED_BY";

    default CtType<Object>  build() {
        CtType<Object> builderClass = getBuilderClass();
        builderClass.addMethod(getSelfOverrideMethod());
        builderClass.addMethod(getBuildMethod());
        builderClass.addModifier(ModifierKind.PUBLIC);
        builderClass.putMetadata(GENERATED_BY, getGeneratedByMetaData());
        return builderClass.clone(); /* TODO: why clone */
    }

    CtType<Object> getBuilderClass();
    String getGeneratedByMetaData();
    CtMethod getBuildMethod();
    CtMethod getSelfOverrideMethod();
}
