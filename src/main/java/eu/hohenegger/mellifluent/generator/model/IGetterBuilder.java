/*-
 * #%L
 * mellifluent-spoon
 * %%
 * Copyright (C) 2020 - 2022 Max Hohenegger <mellifluent-spoon@hohenegger.eu>
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

import java.lang.annotation.Annotation;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.reference.CtVariableReference;

public interface IGetterBuilder {

  CtField<Object> getField();

  Factory getTypeFactory();

  CtMethod<?> getInterfaceMethod();

  default CtMethod<?> build() {
    CtMethod<?> result = getInterfaceMethod().clone();
    result.removeModifier(ModifierKind.ABSTRACT);
    CtTypeReference<Annotation> overrideReference =
        getTypeFactory().createCtTypeReference(Override.class);
    result.addAnnotation(getTypeFactory().createAnnotation(overrideReference));

    CtBlock<?> block = getTypeFactory().createBlock();

    CtReturn<Object> methodReturn = getTypeFactory().createReturn();
    CtVariableRead<Object> read = getTypeFactory().createVariableRead();

    read.setVariable((CtVariableReference<Object>) getField().getReference());
    methodReturn.setReturnedExpression(read);
    block.addStatement(methodReturn);

    result.setBody(block);

    return result;
  }
}
