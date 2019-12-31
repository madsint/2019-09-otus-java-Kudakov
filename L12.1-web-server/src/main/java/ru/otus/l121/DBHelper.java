package ru.otus.l121;

import java.sql.SQLException;

import ru.otus.l121.dataset.*;
import ru.otus.l121.dbservice.*;

public class DBHelper {

	// Simulates DB activity
	public static void startDB(DBService service) throws SQLException, InterruptedException {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						UserDataSet user = new UserDataSet("Johnny Johnny", 35);
						user.setAddress(new AddressDataSet("My Green Street"));
						user.addPhone(new PhoneDataSet("0147258369"));
						user.addPhone(new PhoneDataSet("0123456789"));
						service.save(user);

						user = service.load(user.getId());

						Thread.sleep(500);

						user = service.load(user.getId());

						Thread.sleep(1000);

						user = service.load(user.getId());

						Thread.sleep(500);

						service.loadAddressByUserId(user.getId());

						service.loadPhoneById(1L);

						service.delete(user);
					} catch (Exception e) {

					}
				}
			}

		}).start();

	}
}
