package com.buession.core.utils;

import com.buession.lang.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class URLUtils {

	public final static String buildQuery(final Map<String, Object> data){
		return buildQuery(data, false);
	}

	public final static String buildQuery(final Map<String, Object> data, final boolean urlEncode){
		if(data == null){
			return null;
		}else if(data.size() == 0){
			return Constants.EMPTY_STRING;
		}

		StringBuilder sb = new StringBuilder();

		data.forEach((name, value)->{
			if(sb.length() > 0){
				sb.append('&');
			}

			sb.append(name);
			sb.append('=');

			if(urlEncode && value != null){
				try{
					sb.append(URLEncoder.encode(value.toString(), "UTF-8"));
				}catch(UnsupportedEncodingException e){
					sb.append(value);
				}
			}else{
				sb.append(value);
			}
		});

		return sb.toString();
	}

	public final static String toQueryString(final Map<String, Object> data){
		return buildQuery(data);
	}

	public final static String toQueryString(final Map<String, Object> data, final boolean urlEncode){
		return buildQuery(data, urlEncode);
	}

}
