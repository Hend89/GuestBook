package com.elderstudios.service;

import com.elderstudios.domain.GuestListEntry;
import com.elderstudios.domain.GuestListEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestListService {

    @Autowired
    protected GuestListEntryRepository guestListEntryRepository;
    protected GuestListEntry guestListEntry;


    public GuestListEntry findOne(Long id) {
        GuestListEntry entry = guestListEntryRepository.findOne(id);
        return entry;
    }
    public List<GuestListEntry> findAll() {
        return guestListEntryRepository.findAll();
    }

    public GuestListEntry save(GuestListEntry entry) {
        return guestListEntryRepository.save(entry);
    }


    public GuestListEntry update(Long id, String name, Long contact, String comment) {

        GuestListEntry guestlist = new GuestListEntry();
        guestlist.setId(id);
        guestlist.setName(name);
        guestlist.setComment(comment);
        guestlist.setContact(contact);
        return guestListEntryRepository.save(guestlist);
    }


    public void delete(Long id) {
        guestListEntryRepository.delete(id);
    }
}
