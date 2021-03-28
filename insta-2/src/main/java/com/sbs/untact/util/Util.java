package com.sbs.untact.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
	public static String getNoewDaeStr() {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
				
		Date time = new Date();
				
		return format1.format(time);
	}

	public static Map<String, Object> mapOf(Object...args) {
		if(args.length % 2 != 0) {
			throw new IllegalArgumentException("");
		}
		int size = args.length / 2;
		
		Map<String, Object> map = new LinkedHashMap<>();
		for ( int i = 0; i < size; i++ ) {
			int keyIndex = i * 2;
			int valueIndex = keyIndex + 1;

			String key;
			Object value;

			try {
				key = (String)args[keyIndex];
			}
			catch ( ClassCastException e ) {
				throw new IllegalArgumentException("키는 String으로 입력해야 합니다. " + e.getMessage());
			}

			value = args[valueIndex];

			map.put(key, value);
		}

		return map;
	}
}
