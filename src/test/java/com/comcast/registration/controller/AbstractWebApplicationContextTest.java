package com.comcast.registration.controller;

import com.comcast.registration.configuration.JPATestConfiguration;
import com.comcast.registration.configuration.RegistrationWebMvcConfig;
import com.comcast.registration.configuration.RegistrationWebMvcConfigTest;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Vardhana Rao Gude on 7/13/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RegistrationWebMvcConfigTest.class})
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public abstract class AbstractWebApplicationContextTest extends MockMvcBuilders
{
	@Inject
	private WebApplicationContext wac;

	protected MockMvc mockMvc;

	@PersistenceContext
	private EntityManager entityManager;

	@Before
	public void setup()
	{
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	void flushDBCalls()
	{
		entityManager.flush();
	}
}
