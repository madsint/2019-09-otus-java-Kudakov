package ru.otus.hw03;

import com.sun.management.GarbageCollectionNotificationInfo;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

@SuppressWarnings("restriction")
public class GCMonitor {

	private static final long PERIOD_MS = 60 * 1000;
	private static final Logger logger = Logger.getLogger(GCMonitor.class.getName());

	private static final List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();

	private static Map<String, GCInfo> gcInfoMap = new HashMap<>();

	public static void init() {

		// Subscribe to garbage collector notifications
		for (GarbageCollectorMXBean gcBean : gcBeans) {

			GCInfo gcInfo = new GCInfo(gcBean.getName(), "GC");

			gcInfoMap.put(gcBean.getName(), gcInfo);

			@SuppressWarnings("serial")
			NotificationFilter filter = new NotificationFilter() {
				@Override
				public boolean isNotificationEnabled(Notification notification) {
					return notification.getType()
							.equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION);
				}
			};

			NotificationListener listener = new NotificationListener() {

				@Override
				public void handleNotification(Notification notification, Object handback) {
					CompositeData cd = (CompositeData) notification.getUserData();
					GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from(cd);

					synchronized (gcInfo) {
						gcInfo.addCount();
						gcInfo.addDuration(info.getGcInfo().getDuration());
					}
				}
			};

			NotificationEmitter emitter = (NotificationEmitter) gcBean;
			emitter.addNotificationListener(listener, filter, null);

			logger.log(Level.INFO, "Listening to: " + gcBean.getName());
		}

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				for (GarbageCollectorMXBean gcBean : gcBeans) {

					GCInfo gcInfo = gcInfoMap.get(gcBean.getName());

					synchronized (gcInfo) {
						logger.log(Level.INFO, gcInfo.toString());
						gcInfo.reset();
					}
				}
			}
		};

		// Create timer as a daemon thread and schedule to run after 1 min every 1 min
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(task, PERIOD_MS, PERIOD_MS);

	}
}
