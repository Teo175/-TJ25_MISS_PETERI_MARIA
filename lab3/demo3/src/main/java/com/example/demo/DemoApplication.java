package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@Component class Aluna {}
@Component class Banana {}
@Component class Curmala {}
@Component class Duda {}

@Component
class DemoBean {

	private final Aluna a;

	@Autowired
	private Banana b;  //instantly initialized

	private Curmala c;
	private Duda d;

	public DemoBean(Aluna a) {  //needs to exists in order to start the app
		this.a = a;
		System.out.println("1) Constructor injection: A injected | field B = "+ (b == null));

	}

	@Autowired
	public void setC(Curmala c) {
		this.c = c;
		System.out.println("3) Setter injection: C injected | Field B = " + (b != null));
	}

	@Autowired
	public void injectD(Duda d) {
		this.d = d;
		System.out.println("4) Method injection: D injected | Field D = " + (d != null));
	}

	@PostConstruct
	public void init() {
		System.out.println("5) @PostConstruct: all injected? "
				+ (a != null && b != null && c != null && d != null));
	}
}
