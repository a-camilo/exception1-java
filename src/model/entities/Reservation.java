package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer roomNumber, Date checkin, Date checkout) {

		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public String updateDates(Date checkin, Date checkout) {

		Date now = new Date();
		if (checkin.before(now) || checkout.before(now)) {
			return "Reservation dates for update must be future dates";
		}
		if (!checkout.after(checkin)) {
			return "Check-out date must be after check-in date";
		}

		this.checkin = checkin;
		this.checkout = checkout;
		return null;
	}

	@Override
	public String toString() {
		return "Reservation Room: " + roomNumber + ", checkin:" + sdf.format(checkin) + ", checkout:"
				+ sdf.format(checkout) + ", " + duration() + " nigths";
	}

}
