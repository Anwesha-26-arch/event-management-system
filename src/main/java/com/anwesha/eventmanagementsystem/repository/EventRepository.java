package com.anwesha.eventmanagementsystem.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anwesha.eventmanagementsystem.entity.Event;
import com.anwesha.eventmanagementsystem.entity.EventStatus;




@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	List<Event> findByEventNameContainingIgnoreCase(String name);
	List<Event> findByStatus(EventStatus status);
	long countByStatus(EventStatus status);
}




