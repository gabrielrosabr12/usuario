package com.gabriel.usuario.infraestruture.converter;

import com.gabriel.usuario.infraestruture.dto.UserDTOUpdate;
import com.gabriel.usuario.infraestruture.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
 componentModel = "spring",
 nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapperUpdater {

    /// Copia apenas os valores NÃO NULOS do dto para a entidade de destino
    void updateEntityFromDto(UserDTOUpdate dto, @MappingTarget Users users);
}
