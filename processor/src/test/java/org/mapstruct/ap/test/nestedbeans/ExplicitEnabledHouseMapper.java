/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.nestedbeans;

import org.mapstruct.Mapper;

/**
 * Mapper that explicitly sets disableSubMappingMethodsGeneration to false,
 * which should override the compiler option
 */
@Mapper(disableSubMappingMethodsGeneration = false)
public interface ExplicitEnabledHouseMapper {

    HouseDto houseToHouseDto(House house);
}
