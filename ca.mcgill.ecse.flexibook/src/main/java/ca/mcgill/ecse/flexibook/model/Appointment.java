/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;

// line 85 "../../../../../FlexiBook.ump"
// line 4 "../../../../../FlexiBookStates.ump"
public class Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment State Machines
  public enum Sm { Booked, Final, InProgress }
  private Sm sm;

  //Appointment Associations
  private Customer customer;
  private BookableService bookableService;
  private List<ComboItem> chosenItems;
  private TimeSlot timeSlot;
  private FlexiBook flexiBook;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(Customer aCustomer, BookableService aBookableService, TimeSlot aTimeSlot, FlexiBook aFlexiBook)
  {
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create appointment due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBookableService = setBookableService(aBookableService);
    if (!didAddBookableService)
    {
      throw new RuntimeException("Unable to create appointment due to bookableService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    chosenItems = new ArrayList<ComboItem>();
    if (!setTimeSlot(aTimeSlot))
    {
      throw new RuntimeException("Unable to create Appointment due to aTimeSlot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create appointment due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setSm(Sm.Booked);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getSmFullName()
  {
    String answer = sm.toString();
    return answer;
  }

  public Sm getSm()
  {
    return sm;
  }

  public boolean updateAppointment(TimeSlot TS,boolean isChange,BookableService newService,Boolean isAdd,ComboItem opService)
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case Booked:
        if (upToOneDayDifference()&&timeSlotAvailable(TS))
        {
        // line 15 "../../../../../FlexiBookStates.ump"
          doUpdateAppointment(TS, isChange, newService, isAdd, opService);
          setSm(Sm.Booked);
          wasEventProcessed = true;
          break;
        }
        if (!(upToOneDayDifference())||!(timeSlotAvailable(TS)))
        {
        // line 19 "../../../../../FlexiBookStates.ump"
          rejectUpdateAppointment();
          setSm(Sm.Booked);
          wasEventProcessed = true;
          break;
        }
        break;
      case InProgress:
        if (sameStartTime(TS)&&timeSlotAvailable(TS))
        {
        // line 42 "../../../../../FlexiBookStates.ump"
          doUpdateAppointment(TS, isChange, newService, isAdd, opService);
          setSm(Sm.InProgress);
          wasEventProcessed = true;
          break;
        }
        if (!(upToOneDayDifference())&&!(timeSlotAvailable(TS)))
        {
        // line 46 "../../../../../FlexiBookStates.ump"
          rejectUpdateAppointment();
          setSm(Sm.InProgress);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelAppointment(Customer c)
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case Booked:
        if (upToOneDayDifference())
        {
        // line 23 "../../../../../FlexiBookStates.ump"
          doCancelAppointment(c);
          setSm(Sm.Final);
          wasEventProcessed = true;
          break;
        }
        if (!(upToOneDayDifference()))
        {
        // line 27 "../../../../../FlexiBookStates.ump"
          rejectCancelAppointment();
          setSm(Sm.Booked);
          wasEventProcessed = true;
          break;
        }
        break;
      case InProgress:
        // line 58 "../../../../../FlexiBookStates.ump"
        rejectCancelAppointment();
        setSm(Sm.InProgress);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startAppointment()
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case Booked:
        if (isWithinAppTimeSlot())
        {
        // line 31 "../../../../../FlexiBookStates.ump"
          doStartAppointment();
          setSm(Sm.InProgress);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean registerNoShow()
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case Booked:
        if (isWithinAppTimeSlot()&&noShow())
        {
        // line 35 "../../../../../FlexiBookStates.ump"
          doRegisterNoShow();
          setSm(Sm.Final);
          wasEventProcessed = true;
          break;
        }
        break;
      case InProgress:
        if (appointmentStarted(this))
        {
        // line 50 "../../../../../FlexiBookStates.ump"
          rejectRegisterNoShow();
          setSm(Sm.InProgress);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean endAppointment()
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case InProgress:
        if (isDone())
        {
        // line 54 "../../../../../FlexiBookStates.ump"
          doEndAppointment();
          setSm(Sm.Final);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setSm(Sm aSm)
  {
    sm = aSm;

    // entry actions and do activities
    switch(sm)
    {
      case Final:
        delete();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public BookableService getBookableService()
  {
    return bookableService;
  }
  /* Code from template association_GetMany */
  public ComboItem getChosenItem(int index)
  {
    ComboItem aChosenItem = chosenItems.get(index);
    return aChosenItem;
  }

  public List<ComboItem> getChosenItems()
  {
    List<ComboItem> newChosenItems = Collections.unmodifiableList(chosenItems);
    return newChosenItems;
  }

  public int numberOfChosenItems()
  {
    int number = chosenItems.size();
    return number;
  }

  public boolean hasChosenItems()
  {
    boolean has = chosenItems.size() > 0;
    return has;
  }

  public int indexOfChosenItem(ComboItem aChosenItem)
  {
    int index = chosenItems.indexOf(aChosenItem);
    return index;
  }
  /* Code from template association_GetOne */
  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }
  /* Code from template association_GetOne */
  public FlexiBook getFlexiBook()
  {
    return flexiBook;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeAppointment(this);
    }
    customer.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBookableService(BookableService aBookableService)
  {
    boolean wasSet = false;
    if (aBookableService == null)
    {
      return wasSet;
    }

    BookableService existingBookableService = bookableService;
    bookableService = aBookableService;
    if (existingBookableService != null && !existingBookableService.equals(aBookableService))
    {
      existingBookableService.removeAppointment(this);
    }
    bookableService.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfChosenItems()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addChosenItem(ComboItem aChosenItem)
  {
    boolean wasAdded = false;
    if (chosenItems.contains(aChosenItem)) { return false; }
    chosenItems.add(aChosenItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeChosenItem(ComboItem aChosenItem)
  {
    boolean wasRemoved = false;
    if (chosenItems.contains(aChosenItem))
    {
      chosenItems.remove(aChosenItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addChosenItemAt(ComboItem aChosenItem, int index)
  {  
    boolean wasAdded = false;
    if(addChosenItem(aChosenItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChosenItems()) { index = numberOfChosenItems() - 1; }
      chosenItems.remove(aChosenItem);
      chosenItems.add(index, aChosenItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveChosenItemAt(ComboItem aChosenItem, int index)
  {
    boolean wasAdded = false;
    if(chosenItems.contains(aChosenItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChosenItems()) { index = numberOfChosenItems() - 1; }
      chosenItems.remove(aChosenItem);
      chosenItems.add(index, aChosenItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addChosenItemAt(aChosenItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTimeSlot(TimeSlot aNewTimeSlot)
  {
    boolean wasSet = false;
    if (aNewTimeSlot != null)
    {
      timeSlot = aNewTimeSlot;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setFlexiBook(FlexiBook aFlexiBook)
  {
    boolean wasSet = false;
    if (aFlexiBook == null)
    {
      return wasSet;
    }

    FlexiBook existingFlexiBook = flexiBook;
    flexiBook = aFlexiBook;
    if (existingFlexiBook != null && !existingFlexiBook.equals(aFlexiBook))
    {
      existingFlexiBook.removeAppointment(this);
    }
    flexiBook.addAppointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeAppointment(this);
    }
    BookableService placeholderBookableService = bookableService;
    this.bookableService = null;
    if(placeholderBookableService != null)
    {
      placeholderBookableService.removeAppointment(this);
    }
    chosenItems.clear();
    timeSlot = null;
    FlexiBook placeholderFlexiBook = flexiBook;
    this.flexiBook = null;
    if(placeholderFlexiBook != null)
    {
      placeholderFlexiBook.removeAppointment(this);
    }
  }

  // line 67 "../../../../../FlexiBookStates.ump"
   private void doCancelAppointment(Customer c){
    if(this!=null) {
		if(c.getUsername().equals(customer.getUsername())){
	    	getFlexiBook().removeAppointment(this);
	    }
	}
  }

  // line 75 "../../../../../FlexiBookStates.ump"
   private void doUpdateAppointment(TimeSlot TS, boolean isChange, BookableService newService, Boolean isAdd, ComboItem opService){
    this.setTimeSlot(TS);
	   if(isChange) {    
		   this.setBookableService(newService);
		   return;
	   }
	   
	   if(isAdd!=null) {
		   if(isAdd.equals(Boolean.TRUE)) {
			   this.addChosenItem(opService);
		   }
		   else {
			   this.removeChosenItem(opService);
		   }
	   }
  }

  // line 93 "../../../../../FlexiBookStates.ump"
   private void doStartAppointment(){
    
  }

  // line 97 "../../../../../FlexiBookStates.ump"
   private void doEndAppointment(){
    
  }

  // line 100 "../../../../../FlexiBookStates.ump"
   private boolean isDone(){
    return true;
  }

  // line 104 "../../../../../FlexiBookStates.ump"
   private boolean noShow(){
    return true;
  }

  // line 108 "../../../../../FlexiBookStates.ump"
   private void rejectUpdateAppointment(){
    throw new RuntimeException("unsuccessful");
  }

  // line 112 "../../../../../FlexiBookStates.ump"
   private void rejectCancelAppointment(){
    throw new RuntimeException("unsuccessful");
  }

  // line 116 "../../../../../FlexiBookStates.ump"
   private void rejectRegisterNoShow(){
    throw new RuntimeException("unsuccessful");
  }

  // line 120 "../../../../../FlexiBookStates.ump"
   private boolean isWithinAppTimeSlot(){
    boolean isWithin = false;
		 
		 Date appDate = this.timeSlot.getStartDate();
		 Date systemDate = SystemTime.getSysDate();
		
		 LocalTime appStartTime = this.timeSlot.getStartTime().toLocalTime();
		 LocalTime appEndTime = this.timeSlot.getEndTime().toLocalTime();
		 LocalTime systemTime = SystemTime.getSysTime().toLocalTime();
		 
		 if (appDate.compareTo(systemDate)==0) {
			 if (systemTime.compareTo(appStartTime)>=0 && systemTime.compareTo(appEndTime)<=0) {
			  isWithin = true;
		  }
		 }
		 return isWithin;
  }

  // line 138 "../../../../../FlexiBookStates.ump"
   private boolean upToOneDayDifference(){
    boolean isUpToOneDayBefore = false;	
		Date date1= this.timeSlot.getStartDate();
		Date date2= SystemTime.getSysDate();
		LocalDate localDate1 = date1.toLocalDate();
		LocalDate localDate2 = date2.toLocalDate();
		long noOfDaysBetween = ChronoUnit.DAYS.between(localDate2,localDate1);
		if (noOfDaysBetween>=1) {
		 isUpToOneDayBefore=true;
		}	
		return isUpToOneDayBefore;
  }

  // line 151 "../../../../../FlexiBookStates.ump"
   private boolean sameStartTime(TimeSlot TS){
    if(this.getTimeSlot().getStartDate().compareTo(TS.getStartDate())!=0) return false;
    else {
    	if(this.getTimeSlot().getStartTime().compareTo(TS.getStartTime())!=0) return false;
    	else return true;
    }
  }

  // line 159 "../../../../../FlexiBookStates.ump"
   private boolean timeSlotAvailable(TimeSlot TS){
    // if (!(this.getTimeSlot().getStartTime().compareTo(TS.getStartTime()) == 0) || !(this.getTimeSlot().getStartDate().compareTo(TS.getStartDate()) == 0)) {
			   if(TS.getStartDate().before(SystemTime.getSysDate())) {
				   return false;
			   }
			   Locale locale = new Locale("en");
			   String dayOfTheWeek = getDayString(TS.getStartDate(), locale);
			   if (dayOfTheWeek.equals("Saturday") || dayOfTheWeek.equals("Sunday")){
				   return false;
			   }
			   for(int k = 0; k<flexiBook.getBusiness().getHolidays().size(); k++) {
				   TimeSlot holiday = flexiBook.getBusiness().getHolidays().get(k);
				   for(LocalDate localDate = holiday.getStartDate().toLocalDate(); 
						   localDate.isBefore(holiday.getEndDate().toLocalDate().plusDays(1)); 
						   localDate = localDate.plusDays(1))
				   {
					   Date d  = Date.valueOf(localDate);
				   if(d.compareTo(TS.getStartDate())==0) {
						   if(isOverlap(holiday, TS)) {
							   return false;
						   }
					   }
				   }
			   }
			   
			   for(int k = 0; k<flexiBook.getBusiness().getVacation().size(); k++) {
				   TimeSlot vacation = flexiBook.getBusiness().getVacation().get(k);
				   for(LocalDate localDate = vacation.getStartDate().toLocalDate(); 
						   localDate.isBefore(vacation.getEndDate().toLocalDate().plusDays(1)); 
						   localDate = localDate.plusDays(1))
				   {
					   Date d  = Date.valueOf(localDate);
				   if(d.compareTo(TS.getStartDate())==0) {
						   if(isOverlap(vacation, TS)) {
							   return false;
						   }
					   }
				   }
			   }
			   TimeSlot temp = new TimeSlot(TS.getStartDate(), this.getTimeSlot().getEndTime(), TS.getEndDate(), TS.getEndTime(), flexiBook);
			   boolean successful = false;
			   for(int i=0; i< getUnavailableTimeSlots(TS.getStartDate()).size(); i++) {
				   if(isOverlap(temp, getUnavailableTimeSlots(TS.getStartDate()).get(i))) {
					   for(int j=0; j<flexiBook.getAppointments().size(); j++) {
						   Appointment a = flexiBook.getAppointments().get(j);
						   if(a.getTimeSlot().getStartDate().compareTo(TS.getStartDate())==0) {
							   for(int k=0; k<getDowntimeTimeSlots(a).size(); k++) {
								   TimeSlot downtime = getDowntimeTimeSlots(a).get(k);
								   if(s2_isWithin_s1(getDowntimeTimeSlots(a).get(k), TS)) {
									   successful = true;
								   }
							   }
						   }
					   }
				   }
			   }
			   for (int i=0; i<getAvailableTimeSlots(TS.getStartDate()).size(); i++) {
				   if(s2_isWithin_s1(getAvailableTimeSlots(TS.getStartDate()).get(i), temp)) {
					   successful = true;
					   break;
				   }
			   }
			   if(successful==false) return false;
		//   }
		   return true;
  }

  // line 226 "../../../../../FlexiBookStates.ump"
   private void doRegisterNoShow(){
    int i = this.getCustomer().getNoShow();
    this.getCustomer().setNoShow(i+1);
  }

  // line 233 "../../../../../FlexiBookStates.ump"
   private static  boolean isOverlap(TimeSlot TS1, TimeSlot TS2){
    LocalTime S1 = TS1.getStartTime().toLocalTime();
		LocalTime S2 = TS2.getStartTime().toLocalTime();
		LocalTime E1 = TS1.getEndTime().toLocalTime();
		LocalTime E2 = TS2.getEndTime().toLocalTime();

		return S1.isBefore(E2) && S2.isBefore(E1);
  }

  // line 242 "../../../../../FlexiBookStates.ump"
   private List<TimeSlot> getAvailableTimeSlots(Date date){
    List<TimeSlot> availableTimeSlots = new ArrayList<TimeSlot>();
		Locale locale = new Locale("en");
		String dayOfTheWeek = getDayString(date, locale);
		for (BusinessHour BH : flexiBook.getHours()) {
			if (BH.getDayOfWeek().toString().equals(dayOfTheWeek)) {
				TimeSlot TS = new TimeSlot (date, BH.getStartTime(), date, BH.getEndTime(), flexiBook);
				availableTimeSlots.add(TS);
			}
		}
		for (Appointment appointment : flexiBook.getAppointments()) {
			if(appointment.getTimeSlot().getStartDate().compareTo(date) == 0) {
				TimeSlot appTS = appointment.getTimeSlot();
				if (availableTimeSlots.size()!=0) {
					for(int i = 0; i<availableTimeSlots.size(); i++) {
						TimeSlot TS = availableTimeSlots.get(i);
						if(isOverlap(appTS, TS)) {

							LocalTime S1 = appTS.getStartTime().toLocalTime();
							LocalTime S2 = TS.getStartTime().toLocalTime();
							LocalTime E1 = appTS.getEndTime().toLocalTime();
							LocalTime E2 = TS.getEndTime().toLocalTime();

							if (S1.compareTo(S2) == 0 && E1.compareTo(E2)==0) {
								availableTimeSlots.remove(TS);
							}
							else if(S1.compareTo(S2) == 0) {
								TimeSlot tmp = new TimeSlot(date, appTS.getEndTime(), date, TS.getEndTime(), flexiBook);
								availableTimeSlots.add(tmp);
								availableTimeSlots.remove(TS);
							}
							else if(E1.compareTo(E2)==0) {
								TimeSlot tmp = new TimeSlot(date, TS.getStartTime(), date, appTS.getStartTime(), flexiBook);
								availableTimeSlots.add(tmp);
								availableTimeSlots.remove(TS);
							}
							else {
								TimeSlot tmp1 = new TimeSlot(date, TS.getStartTime(), date, appTS.getStartTime(), flexiBook);
								TimeSlot tmp2 = new TimeSlot(date, appTS.getEndTime(), date, TS.getEndTime(), flexiBook);
								availableTimeSlots.remove(TS);
								availableTimeSlots.add(tmp1);
								availableTimeSlots.add(tmp2);
							}
						}
						for(int j = 0; i<getDowntimeTimeSlots(appointment).size();i++) {
							TimeSlot downtime = getDowntimeTimeSlots(appointment).get(j);
							availableTimeSlots.add(downtime);

						}
					}
				}

			}
		}

		for(int k = 0; k<flexiBook.getBusiness().getHolidays().size();k++) {
			TimeSlot holiday = flexiBook.getBusiness().getHolidays().get(k);
			for(LocalDate localDate = holiday.getStartDate().toLocalDate(); 
					localDate.isBefore(holiday.getEndDate().toLocalDate().plusDays(1)); 
					localDate = localDate.plusDays(1))
			{
				Date d  = Date.valueOf(localDate);
				if(d.compareTo(date)==0) {
					for(int i = 0; i<availableTimeSlots.size(); i++) {
						availableTimeSlots.remove(i);
					}
				}

			}
		}

		return availableTimeSlots;
  }

  // line 318 "../../../../../FlexiBookStates.ump"
   private static  String getDayString(Date date, Locale locale){
    DateFormat formatter = new SimpleDateFormat("EEEE", locale);
		return formatter.format(date);
  }

  // line 323 "../../../../../FlexiBookStates.ump"
   private List<TimeSlot> getDowntimeTimeSlots(Appointment app){
    List<TimeSlot> downtimeTimeSlots = new ArrayList<TimeSlot>();
		BookableService S = app.getBookableService();
		if(S instanceof Service) {
			Service service = (Service) S;
			if (service.getDowntimeDuration() != 0) {
				LocalTime startTime = app.getTimeSlot().getStartTime().toLocalTime().plusMinutes(service.getDowntimeStart());
				LocalTime endTime = startTime.plusMinutes(service.getDowntimeDuration());
				Time start = Time.valueOf(startTime);
				Time end = Time.valueOf(endTime);
				TimeSlot TS = new TimeSlot(app.getTimeSlot().getStartDate(), start, app.getTimeSlot().getStartDate(), end, flexiBook);
				downtimeTimeSlots.add(TS);
			}
		}else if(S instanceof ServiceCombo) {
			int minutes = 0;
			ServiceCombo combo = (ServiceCombo) S;
			for (ComboItem item : combo.getServices()) {
				Service s = item.getService();
				minutes += s.getDuration(); 
				if (s.getDowntimeDuration() != 0) {
					minutes -= s.getDuration();
					LocalTime startTime = app.getTimeSlot().getStartTime().toLocalTime().plusMinutes(s.getDowntimeStart() + minutes);
					LocalTime endTime = startTime.plusMinutes(s.getDowntimeDuration());
					Time start = Time.valueOf(startTime);
					Time end = Time.valueOf(endTime);
					TimeSlot TS = new TimeSlot(app.getTimeSlot().getStartDate(), start, app.getTimeSlot().getStartDate(), end, flexiBook);
					downtimeTimeSlots.add(TS);
				}
			}

		}


		return downtimeTimeSlots;
  }

  // line 361 "../../../../../FlexiBookStates.ump"
   private List<TimeSlot> getUnavailableTimeSlots(Date date){
    List<TimeSlot> unavailableTimeSlots = new ArrayList<TimeSlot>();
		
		Locale locale = new Locale("en");
		String dayOfTheWeek = getDayString(date, locale);
		for (BusinessHour BH : flexiBook.getHours()) {
			if (BH.getDayOfWeek().toString().equals(dayOfTheWeek)) {
				TimeSlot TS = new TimeSlot (date, BH.getStartTime(), date, BH.getEndTime(), flexiBook);
				unavailableTimeSlots.add(TS);
			}	
		}
		List<TimeSlot> available = getAvailableTimeSlots(date);

		for (int i = 0; i<available.size(); i++) {

			TimeSlot av = available.get(i);

			for (int j = 0; j<unavailableTimeSlots.size(); j++) {
				TimeSlot un = unavailableTimeSlots.get(j);
				if(isOverlap(av, un)) {

					LocalTime S1 = av.getStartTime().toLocalTime();
					LocalTime S2 = un.getStartTime().toLocalTime();
					LocalTime E1 = av.getEndTime().toLocalTime();
					LocalTime E2 = un.getEndTime().toLocalTime();

					if (S1.compareTo(S2) == 0 && E1.compareTo(E2)==0) {
						unavailableTimeSlots.remove(un);
					}
					else if(S1.compareTo(S2) == 0) {
						TimeSlot tmp = new TimeSlot(date, av.getEndTime(), date, un.getEndTime(), flexiBook);
						unavailableTimeSlots.add(tmp);
						unavailableTimeSlots.remove(un);
					}
					else if(E1.compareTo(E2)==0) {
						TimeSlot tmp = new TimeSlot(date, un.getStartTime(), date, av.getStartTime(), flexiBook);
						unavailableTimeSlots.add(tmp);
						unavailableTimeSlots.remove(un);
					}
					else {
						TimeSlot tmp1 = new TimeSlot(date, un.getStartTime(), date, av.getStartTime(), flexiBook);
						TimeSlot tmp2 = new TimeSlot(date, av.getEndTime(), date, un.getEndTime(), flexiBook);
						unavailableTimeSlots.remove(un);
						unavailableTimeSlots.add(tmp1);
						unavailableTimeSlots.add(tmp2);
					}
				}

			}
		}

		return unavailableTimeSlots;
  }

  // line 415 "../../../../../FlexiBookStates.ump"
   private static  boolean s2_isWithin_s1(TimeSlot S1, TimeSlot S2){
    boolean isWithin = false;
			
			LocalTime startTime1 = S1.getStartTime().toLocalTime();
			LocalTime startTime2 = S2.getStartTime().toLocalTime();
			LocalTime endTime1 = S1.getEndTime().toLocalTime();
			LocalTime endTime2 = S2.getEndTime().toLocalTime();
			
			Date date1 = S1.getStartDate();
			Date date2 = S2.getStartDate();
			
			if(startTime1.compareTo(startTime2)<0 || startTime1.compareTo(startTime2)==0) {
				if(endTime1.compareTo(endTime2)>0 || endTime1.compareTo(endTime2)==0){
					isWithin = true;
				}
			}
		return isWithin;
  }

  // line 434 "../../../../../FlexiBookStates.ump"
   private boolean appointmentStarted(Appointment a){
    if (a.getSm()!=Appointment.Sm.Booked){
		return true;
		}
		return false;
  }

}