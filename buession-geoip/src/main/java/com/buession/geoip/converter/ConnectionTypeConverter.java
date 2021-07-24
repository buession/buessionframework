package com.buession.geoip.converter;

import com.buession.core.converter.Converter;
import com.buession.geoip.model.ConnectionType;
import com.maxmind.geoip2.model.ConnectionTypeResponse;

/**
 * @author Yong.Teng
 * @since 1.3.0
 */
class ConnectionTypeConverter implements Converter<ConnectionTypeResponse.ConnectionType,
		com.buession.geoip.model.ConnectionType> {

	@Override
	public ConnectionType convert(final ConnectionTypeResponse.ConnectionType source){
		if(source == null){
			return null;
		}

		switch(source){
			case DIALUP:
				return ConnectionType.DIALUP;
			case CABLE_DSL:
				return ConnectionType.CABLE_DSL;
			case CORPORATE:
				return ConnectionType.CORPORATE;
			case CELLULAR:
				return ConnectionType.CELLULAR;
			default:
				break;
		}

		return null;
	}

}
