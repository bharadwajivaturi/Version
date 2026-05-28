package com.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.Version;
import com.app.Repository.VersionRepository;

@RestController
@RequestMapping("/version")
public class VersionController
{
	@Autowired
	private VersionRepository versionRepository;
	
	@PostMapping("/post")
	public ResponseEntity<?> versionPost(@RequestParam Double version)
	{
		try
		{
		    Version v = new Version();
		    v.setVersion(version);
		    Version versionSave = versionRepository.save(v);
		    return ResponseEntity.ok(versionSave);
		}
		catch (Exception e)
		{
		    return ResponseEntity.status(500).body("Error saving version: " + e.getMessage());
		}

	}
	
	@GetMapping("/get")
	public ResponseEntity<?> versionGet()
	{
	    try
	    {
	        return ResponseEntity.ok(versionRepository.findAll());
	    }
	    catch (Exception e)
	    {
	        return ResponseEntity.status(500).body("Error fetching versions: " + e.getMessage());
	    }
	}
	
	@GetMapping("/getById")
	public ResponseEntity<?> versionGetById(@RequestParam Long id)
	{
	    try
	    {
	        Version version = versionRepository.findById(id).orElse(null);

	        if (version == null)
	        {
	            return ResponseEntity.status(404).body("Version with ID " + id + " not found.");
	        }

	        return ResponseEntity.ok(version);
	    }
	    catch (Exception e)
	    {
	        return ResponseEntity.status(500).body("Error fetching version: " + e.getMessage());
	    }
	}

	
	@GetMapping("/getByVersion")
	public ResponseEntity<?> versionGetByVersion(@RequestParam Double version)
	{
	    try
	    {
	        Version v = versionRepository.findByVersion(version);

	        if (v == null)
	        {
	            return ResponseEntity.status(404).body("Version " + version + " not found.");
	        }

	        return ResponseEntity.ok(v);
	    }
	    catch (Exception e)
	    {
	        return ResponseEntity.status(500).body("Error fetching version: " + e.getMessage());
	    }
	}
	
	@PutMapping("/updateById")
	public ResponseEntity<?> versionUpdateById(
	        @RequestParam Long id,
	        @RequestParam Double version) {

	    try {
	        Version existing = versionRepository.findById(id).orElse(null);

	        if (existing == null) {
	            return ResponseEntity.status(404).body("Version with ID " + id + " not found.");
	        }

	        existing.setVersion(version);
	        Version updated = versionRepository.save(existing);

	        return ResponseEntity.ok(updated);
	    }
	    catch (Exception e) {
	        return ResponseEntity.status(500).body("Error updating version: " + e.getMessage());
	    }
	}

	
	@PutMapping("/updateByVersion")
	public ResponseEntity<?> updateByVersion(
	        @RequestParam Double oldVersion,
	        @RequestParam Double newVersion) {

	    try {
	        Version existing = versionRepository.findByVersion(oldVersion);

	        if (existing == null) {
	            return ResponseEntity.status(404).body("Version " + oldVersion + " not found.");
	        }

	        existing.setVersion(newVersion);
	        Version updated = versionRepository.save(existing);

	        return ResponseEntity.ok(updated);
	    }
	    catch (Exception e) {
	        return ResponseEntity.status(500).body("Error updating version: " + e.getMessage());
	    }
	}

	
	@DeleteMapping("/deleteById")
	public ResponseEntity<?> versionDelete(@RequestParam Long id)
	{
	    try
	    {
	        if (!versionRepository.existsById(id))
	        {
	            return ResponseEntity.status(404).body("Version with ID " + id + " not found.");
	        }

	        versionRepository.deleteById(id);
	        return ResponseEntity.ok("Version deleted successfully.");
	    }
	    catch (Exception e)
	    {
	        return ResponseEntity.status(500).body("Error deleting version: " + e.getMessage());
	    }
	}
	
	@DeleteMapping("/deleteByVersion")
	public ResponseEntity<?> deleteByVersion(@RequestParam Double version) {

	    try {
	        if (!versionRepository.existsByVersion(version)) {
	            return ResponseEntity.status(404).body("Version " + version + " not found.");
	        }

	        versionRepository.deleteByVersion(version);
	        return ResponseEntity.ok("Version deleted successfully.");
	    }
	    catch (Exception e) {
	        return ResponseEntity.status(500).body("Error deleting version: " + e.getMessage());
	    }
	}
 }