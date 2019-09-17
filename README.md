# Parking Lot

## Usage

Create parking lot with number of slots for the car and the billing policy.

```java
ParkingLot parkingLot = new ParkingLot.Builder().addSlots(ParkingSlotType.SLOT_20KV, 6)
				.addSlots(ParkingSlotType.SLOT_50KV, 6)
				.addBillingPolicy(new DefaultBillingPolicy.Builder().perHour(10).build()).build();
```

Once, ParkingLot is created it is ready to create ticket for new entry of car.

```java
Ticket allotedTicket = parkingLot.assignParking("AB11AA3939", VehicleType.CAR_20KV);
allotedTicket.print();
```
Sample Ticket:

```
============Ticket: TKT-0============
Ticket Number = TKT-0
Issued at = Tue Sep 17 14:53:16 IST 2019
Payed at = null
Total Bill = 0.0
Ticket Status = ACTIVE
-------------------------------------------
Parking Slot Number = 0
Vehicle Number = AB11AA3939
Vehicle type = CAR_20KV
===========================================
```

While exit of the car, on submitting the ticket, it will provide the parking charges:

```java
double parkingFees = parkingLot.vehicleExit("TKT-0");
System.out.println("Parking fees :" + parkingFees);
```

If ticket reference will be available, then it will be updated in the ticket as well.

```
============Ticket: TKT-0============
Ticket Number = TKT-0
Issued at = Tue Sep 17 14:53:16 IST 2019
Payed at = null
Total Bill = 40.0
Ticket Status = PAID
-------------------------------------------
Parking Slot Number = 0
Vehicle Number = AB11AA3939
Vehicle type = CAR_20KV
===========================================
```

## Error Code
ERR01 : Parking is Full.
ERR04 : Invalid Ticket ID.

## Limitations:

* Adding new type of parking slot is not supported ( currently 3 type of parking slots are supported : standard, a slot with 20kw power and a slot with 50kw power ).

* Parking number starts from zero (0).

## Assumptions

* When exit API is called, it is assumed that, the car owner has paid the amount.
