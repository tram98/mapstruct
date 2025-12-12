/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.nestedbeans;

import org.mapstruct.ap.testutil.ProcessorTest;
import org.mapstruct.ap.testutil.WithClasses;
import org.mapstruct.ap.testutil.compilation.annotation.CompilationResult;
import org.mapstruct.ap.testutil.compilation.annotation.Diagnostic;
import org.mapstruct.ap.testutil.compilation.annotation.ExpectedCompilationOutcome;
import org.mapstruct.ap.testutil.compilation.annotation.ProcessorOption;

/**
 * @author Filip Hrisafov
 */
@WithClasses({
    House.class, HouseDto.class,
    Wheel.class, WheelDto.class,
    Roof.class, RoofDto.class,
    RoofType.class, ExternalRoofType.class
})
public class DisablingNestedSimpleBeansMappingTest {

    @WithClasses({
        ErroneousDisabledHouseMapper.class
    })
    @ExpectedCompilationOutcome(value = CompilationResult.FAILED,
        diagnostics = {
            @Diagnostic(type = ErroneousDisabledHouseMapper.class,
                kind = javax.tools.Diagnostic.Kind.ERROR,
                line = 16,
                message = "Can't map property \"Roof roof\" to \"RoofDto roof\". " +
                    "Consider to declare/implement a mapping method: \"RoofDto map(Roof value)\"."
            )
        })
    @ProcessorTest
    public void shouldUseDisabledMethodGenerationOnMapper() {
    }

    @WithClasses({
        ErroneousDisabledViaConfigHouseMapper.class,
        DisableConfig.class
    })
    @ExpectedCompilationOutcome(value = CompilationResult.FAILED,
        diagnostics = {
            @Diagnostic(type = ErroneousDisabledViaConfigHouseMapper.class,
                kind = javax.tools.Diagnostic.Kind.ERROR,
                line = 16,
                message = "Can't map property \"Roof roof\" to \"RoofDto roof\". " +
                    "Consider to declare/implement a mapping method: \"RoofDto map(Roof value)\"."
            )
        })
    @ProcessorTest
    public void shouldUseDisabledMethodGenerationOnMapperConfig() {
    }

    @WithClasses({
        ErroneousDisabledViaCompilerOptionHouseMapper.class
    })
    @ProcessorOption(name = "mapstruct.defaultDisableSubMappingMethodsGeneration", value = "true")
    @ExpectedCompilationOutcome(value = CompilationResult.FAILED,
        diagnostics = {
            @Diagnostic(type = ErroneousDisabledViaCompilerOptionHouseMapper.class,
                kind = javax.tools.Diagnostic.Kind.ERROR,
                line = 13,
                message = "Can't map property \"Roof roof\" to \"RoofDto roof\". " +
                    "Consider to declare/implement a mapping method: \"RoofDto map(Roof value)\"."
            )
        })
    @ProcessorTest
    public void shouldUseDisabledMethodGenerationViaCompilerOption() {
    }

    @WithClasses({
        ExplicitEnabledHouseMapper.class
    })
    @ProcessorOption(name = "mapstruct.defaultDisableSubMappingMethodsGeneration", value = "true")
    @ExpectedCompilationOutcome(value = CompilationResult.SUCCEEDED)
    @ProcessorTest
    public void shouldAllowAnnotationToOverrideCompilerOption() {
    }
}
