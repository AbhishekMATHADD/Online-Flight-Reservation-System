package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Flight;
import com.example.demo.Model.FlightSchedule;
import com.example.demo.Model.Payment;
import com.example.demo.Model.Reservation;
import com.example.demo.Model.User;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.FlightScheduleService;
import com.example.demo.Service.FlightService;
import com.example.demo.Service.PaymentService;
import com.example.demo.Service.ReservationService;
import com.example.demo.Service.UserService;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private ReservationService resoService;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customService;

	@Autowired
	private PaymentService payService;

	@Autowired
	private FlightScheduleService flightScheduleService;

	@GetMapping(path = "/demo")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/add_flight")
	public String addFlight(Model model) {
		Flight flight = new Flight();
		model.addAttribute("flight", flight);
		return "addFlight";
	}

	@PostMapping("/add_flight/save")
	public String saveFlight(@ModelAttribute("flight") Flight flight) throws ParseException {

		flightService.save(flight);
		return "index";
	}

	@GetMapping("/flight_details")
	public String flightDetailsList(Model model) {
		List<Flight> fightList = flightService.listAll();
		model.addAttribute("flightList", fightList);
		return "flightDetails";
	}

	@GetMapping("/admin/flight_details")
	public String AdminflightDetailsList(Model model) {
		List<Flight> fightList = flightService.listAll();
		model.addAttribute("flightList", fightList);
		return "adminFlightDetails";
	}

	@GetMapping("/flight_details/add_schedule/{id}")
	public String addSchedule(@PathVariable(name = "id") Long id, Model model) {
		FlightSchedule flightSchedule = new FlightSchedule();
		Flight flight = flightService.getFlightDetails(id);
		model.addAttribute("flightSchedule", flightSchedule);
		model.addAttribute("flight", flight);
		return "addFlightSchedule";
	}

	@PostMapping("/flight_details/add_schedule/save")
	public String saveSchedule(@ModelAttribute("flightSchedule") FlightSchedule flightSchedule,
			@ModelAttribute("flight") Flight flight) {
		flightScheduleService.saveFlightSchedule(flightSchedule, flight);
		return "redirect:/demo/flight_details";
	}

	/*
	 * @GetMapping("/admin/add_schedule/delete_schedu") public String
	 * deleteSchedule(@PathVariable(name="id") Long id, @PathVariable(name="idd")
	 * Long idd) { Flight flight = flightService.getFlightDetails(id);
	 * FlightSchedule flightSchedule =
	 * flightScheduleService.getFlightScheduleByID(idd);
	 * flightScheduleService.deleteFlightSchedule(idd); return "index"; }
	 * 
	 */

	@GetMapping("/flight_details/edit_flight/{id}")
	public ModelAndView editFlightDetails(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("editFlightDetail");
		Flight flight = flightService.getFlightDetails(id);
		mav.addObject("flight", flight);
		return mav;
	}

	@PostMapping("/flight_details/edit_flight/save")
	public String saveFlightDetails(@ModelAttribute("flight") Flight flight) throws ParseException {
		flightService.save(flight);
		return "redirect:/demo/flight_details";
	}

	@GetMapping("/flight_details/delete_flight/{id}")
	public String deleteFlight(@PathVariable(name = "id") Long id) {
		flightService.deleteFlightById(id);
		return "redirect:/demo/flight_details";
	}

	/*
	 * 
	 * @GetMapping("/add_schedule") public String addFlightSchedule(Model model) {
	 * FlightSchedule flightSchedule = new FlightSchedule();
	 * model.addAttribute("flightSchedule", flightSchedule); return
	 * "addFlightSchedule"; }
	 * 
	 * 
	 * 
	 * 
	 * @PostMapping("/add_schedule/save") public String
	 * saveSchedule(@ModelAttribute("flightSchedule") FlightSchedule flightSchedule)
	 * { flightScheduleService.saveFlightSchedule(flightSchedule); return "index"; }
	 * 
	 */

	/*
	 * 
	 * @GetMapping("add_schedule/{id}") public String
	 * addFlightSchedule(@PathVariable(name="id") Long id, Model model) {
	 * FlightSchedule flightSchedule = new FlightSchedule(); Flight flight =
	 * flightService.getFlightDetails(id); model.addAttribute("flightSchedule",
	 * flightSchedule); model.addAttribute("flight",flight); return
	 * "addFlightSchedule"; }
	 * 
	 * 
	 * 
	 * @PostMapping("add_schedule/save") public String
	 * saveSchedule(@ModelAttribute("flightSchedule")FlightSchedule
	 * flightSchedule, @ModelAttribute("flight") Flight flight) {
	 * flightScheduleService.saveFlightSchedule(flightSchedule, flight); return
	 * "index"; }
	 * 
	 * 
	 * 
	 * @GetMapping("add_schedule/delete_schedule/{id}") public String
	 * deleteSchedule(@PathVariable(name="id") Long id) {
	 * flightScheduleService.deleteFlightSchedule(id); return
	 * "redirect:/demo/admin/flight_details"; }
	 * 
	 * 
	 * 
	 * @PostMapping("/flight_details/add_schedule/save") public String
	 * saveSchedule(@ModelAttribute("flightSchedule") FlightSchedule
	 * flightSchedule, @ModelAttribute("flight") Flight flight) {
	 * //resoService.saveReservation(reservation, flight);
	 * flightScheduleService.saveFlightSchedule(flightSchedule, flight); return
	 * "redirect:/demo/flight_details"; }
	 * 
	 */

	@GetMapping("/flight_details/book_ticket/{id}")
	public String flightDetailsList(@PathVariable(name = "id") Long id, Model model) {
		Reservation reservation = new Reservation();
		Flight flight = flightService.getFlightDetails(id);
		model.addAttribute("flight", flight);
		model.addAttribute("reservation", reservation);
		return "Book";
	}

	/*
	 * @PostMapping("/flight_details/book_ticket/save/passenger/save") public String
	 * savePassengerDetails(@ModelAttribute("reservation") Customer passenger
	 * ,@ModelAttribute("reservation") Reservation reservation) {
	 * customService.savePassenger(passenger, reservation); return
	 * "redirect:/demo/flight_details"; }
	 */

	@PostMapping("/flight_details/book_ticket/save")
	public String bookReservation(@ModelAttribute("reservation") Reservation reservation,
			@ModelAttribute("flight") Flight flight) {
		// resoService.saveReservation(reservation, flight);
		Reservation r = resoService.saveReservation(reservation, flight);
		System.out.println(r.getId());
		return "redirect:/demo/flight_details/book_ticket/save/passenger/" + r.getId();
	}

	@GetMapping("/flight_details/book_ticket/save/passenger/{id}")
	public String addPassengerDetails(@PathVariable(name = "id") Long id, Model model) {

		Reservation res = resoService.getReservationID(id);
		model.addAttribute("customer", new Customer());
		model.addAttribute("reservation", res);
		return "meaditor";
	}

	@PostMapping("/flight_details/book_ticket/save/passenger/save")
	public String savePassengers(@ModelAttribute("customer") Customer customer,
			@ModelAttribute("reservation") Reservation reservation) {
		Customer passenger = customService.savePassenger(customer, reservation);
		Long id = passenger.getReservation().getId();
		Reservation res = resoService.getReservationID(id);
		System.out.print(res.getId());
		return "redirect:/demo/flight_details/book_ticket/save/passenger/save/payment/" + res.getId();
	}

	@GetMapping("/flight_details/book_ticket/save/passenger/save/payment/{id}")
	public String addPayment(@PathVariable(name = "id") Long id, Model model) {
		Reservation res = resoService.getReservationID(id);
		Long seats;
		if (res.getReservationType() == "FirstClass") {
			seats = (long) (res.getNoOfSetas() * 5000);
		} else if (res.getReservationType() == "MiddleClasss") {
			seats = (long) (res.getNoOfSetas() * 10000);
		} else {
			seats = (long) (res.getNoOfSetas() * 15000);
		}

		Payment payment = new Payment();
		payment.setAmount(seats);
		model.addAttribute("payment", payment);
		model.addAttribute("reservation", res);
		return "PassengerAdd";
	}

	@PostMapping("/flight_details/book_ticket/save/passenger/save/payment/save")
	public String paymentUpdate(@ModelAttribute("payment") Payment payment,
			@ModelAttribute("reservation") Reservation reservation) throws ParseException {
		Payment pay = payService.savePayment(payment, reservation);
		return "redirect:/demo/flight_details/book_ticket/generated_ticket/" + reservation.getId() + "/" + pay.getId();
	}

	@GetMapping("/flight_details/book_ticket/generated_ticket/{id}/{idd}")
	public String genrateTicket(@PathVariable(name = "id") Long id, @PathVariable(name = "idd") Long idd, Model model) {
		Reservation reservation = resoService.getReservationID(id);
		Flight flight = reservation.getFlight();
		Payment pay = payService.getPayment(idd);
		System.out.println(flight.getId());
		System.out.println(reservation.getId());
		System.out.println(pay.getId());
		model.addAttribute("reservation", reservation);
		model.addAttribute("flight", flight);
		model.addAttribute("pay", pay);
		return "Ticket";
	}

	/*
	 * @GetMapping("/flight_details/book_ticket/save/passenger") public String
	 * addPassengerDetails(@RequestParam(name="id") Long id, Model model) {
	 * /*Customer customer = new Customer(); Reservation reservation =
	 * resoService.getReservationID(id); model.addAttribute("customer", customer);
	 * model.addAttribute("reservation", reservation); return
	 * "redirect://demo/flight_details";
	 * 
	 * }
	 * 
	 * 
	 * @PostMapping("/flight_details/book_ticket/save/passenger/save") public String
	 * bookReservation(@ModelAttribute("passenger") Customer
	 * passenger, @ModelAttribute("reservation") Reservation reservation ) {
	 * customService.saePassenger(passenger, reservation); return ""; }
	 * 
	 */

}
