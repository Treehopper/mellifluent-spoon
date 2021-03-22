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

import static spoon.reflect.declaration.ModifierKind.PRIVATE;

import spoon.reflect.declaration.CtField;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;

public interface IFieldBuilder {

    default CtField<Object>  build() {
        CtField<Object> ctField = getTypeFactory().createField();
        ctField.addModifier(PRIVATE);
        ctField.setType(getGetterDeclaringType());
        ctField.setSimpleName(getSimpleName());
        return ctField;
    }

    CtTypeReference getGetterDeclaringType();
    String getSimpleName();

    Factory getTypeFactory();
}
