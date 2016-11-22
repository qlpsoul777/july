package com.qlp.core.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.qlp.core.enums.StatusEnum;

@Converter
public class StatusEnumConverter implements
		AttributeConverter<StatusEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(StatusEnum attribute) {
		if ( attribute == null ) {
            return null;
        }
		return attribute.getCode();
	}

	@Override
	public StatusEnum convertToEntityAttribute(Integer dbData) {
		if ( dbData == null ) {
            return null;
        }
		return StatusEnum.from(dbData);
	}

}
