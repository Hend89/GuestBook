package com.elderstudios.controller;

import com.elderstudios.domain.GuestListEntry;
import com.elderstudios.service.GuestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@Controller
public class HelloController {

	@Autowired
	protected GuestListService guestListService;

	private static final String guests = "guestlist";
	private static final String ENTRIES_KEY = "entries";
	private static final String FORM_KEY = "form";
	private static final String FORM_EDIT = "edit";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayGuestlist( Model model ) {

		model.addAttribute(ENTRIES_KEY, guestListService.findAll());
		model.addAttribute(FORM_KEY, new GuestListEntry());

		return guests;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String changeGuestlist(
			Model model,
			@Valid @ModelAttribute(FORM_KEY) GuestListEntry form,
			BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, guestListService.findAll());
			return guests;
		}

		guestListService.save(form);

		return "redirect:/";
	}

	@RequestMapping("edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		model.addAttribute(FORM_EDIT, guestListService.findOne(id));
		return FORM_EDIT;
	}

	@RequestMapping(value = "/update{id, name, comment, contact}", method = RequestMethod.GET)
	public String updateGuestlist(
			@PathVariable Long id,
			@PathVariable String name,
			@PathVariable Long  contact,
			@PathVariable String comment,
			BindingResult bindingResult ) {



		//model.addAttribute(FORM_EDIT, guestListService.findOne(id));


		guestListService.update(id, name, contact, comment);

		return "redirect:/";
	}

	@RequestMapping("delete/{id}")
	public String delete(@PathVariable Long id){
		guestListService.delete(id);
		return "redirect:/";
	}
}