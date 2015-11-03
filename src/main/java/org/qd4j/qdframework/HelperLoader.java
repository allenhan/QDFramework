package org.qd4j.qdframework;

import org.qd4j.qdframework.helper.BeanHelper;
import org.qd4j.qdframework.helper.ClassHelper;
import org.qd4j.qdframework.helper.ControllerHelper;
import org.qd4j.qdframework.helper.IOCHelper;
import org.qd4j.qdframework.util.ClassUtil;

public class HelperLoader {
	public static void init() {
		Class<?>[] classes = { BeanHelper.class, ClassHelper.class, ControllerHelper.class, IOCHelper.class };
		for (Class<?> class1 : classes) {
			ClassUtil.loadClass(class1.getName(), false);
		}
	}
}
