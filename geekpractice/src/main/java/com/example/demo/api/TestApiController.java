package com.example.demo.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.UsersData;
import com.example.demo.util.ExcelOrder;


@RestController
@RequestMapping("/api") //テスト用 http://localhost:8080/api/
public class TestApiController {
	
	@Autowired
	private UsersApiService usersApiService;
	
	@Autowired
	private GoodsApiService goodsApiService;
	
    @Autowired
    private ExcelOrder excelOrder;

	@GetMapping("/nuxthome")
	public void nuxthome() {
	}
	
	@GetMapping("/users/list")
	public List<UsersApiData> getUserslist() {
		return usersApiService.getUsersList();
	}
	      
	
	@GetMapping("/users/{id}")
	public UsersData getUser(@PathVariable Integer id) {
	    return usersApiService.getUserById(id);
	}
	
	@GetMapping("/goods/list")
	public List<GoodsApiData> getGoodslist() {
		return goodsApiService.getGoodsList();
	}

    @GetMapping("ledger/create")
    public ResponseEntity<Resource> exportOrders(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) throws Exception{

    	String fileName = excelOrder.exportOrderDetail(start, end);
    	
    	System.out.println("=== File DL STRAT===");
		Path path = Paths.get("src/main/resources/public/" + fileName);
		if (!Files.exists(path)) {
			System.out.println("=== Path ON! ===");
	        return ResponseEntity.notFound().build();
	    }
		Resource resource = new PathResource(path);
		System.out.println("=== Path GET! ===");
		return ResponseEntity.ok()
                .contentType(getContentType(path))
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
		}
    	
    private MediaType getContentType(Path path) throws IOException {
        try {
            return MediaType.parseMediaType(Files.probeContentType(path));
        } catch (IOException e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
    
    private final String EXCEL_DIR = "src/main/resources/public/";
    @GetMapping("ledger/list")
    public List<String> listExcelFiles() {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:/public/*.xlsx");

            return Arrays.stream(resources)
                    .map(Resource::getFilename)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
	    

