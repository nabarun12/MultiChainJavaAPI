/*
 * Copyright (C) 2017 Worldline, Inc.
 *
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * https://github.com/SimplyUb/MultiChainJavaAPI/blob/master/LICENSE
 *
 */
package multichain.test.command;

import java.util.List;

import multichain.command.MultiChainCommand;
import multichain.command.MultichainException;
import multichain.object.Address;
import multichain.object.Permission;

/**
 * @author Ub - H. MARTEAU
 * @version 2.0
 */
public class TestGeneral {
	static MultiChainCommand multiChainCommand;

	/**
	 *
	 */
	public TestGeneral() {
		// TODO Auto-generated constructor stub
	}

	static String streamName = "ratingsystem2";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("--- Start of AddressCommandTest ---");

		// BlockChain TestCommand has to be created and started before
		multiChainCommand = new MultiChainCommand("localhost", "9584", "multichainrpc",
				"multichainrpc");
		
		testcreate();
		testsubscribe();
		for(String address : testgetAddresses()){
			testReadWritePermissions(address);
			testWritePermissions(address);
			
		}
		
		

		System.out.println("--- End of AddressCommandTest ---");
	}
	
	private static List<String> testgetAddresses() {
		List<String> result = null;
		try {
			result = multiChainCommand.getAddressCommand().getAddresses();
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == null || result.size() == 0) {
			System.err.println("testgetAddresses - result list is empty");
		} else {
			System.out.println("testgetAddresses - Result :");
			for (String ad : result) {
				System.out.println(ad);
			}
		}
		
		return result;
		
	}
	
	private static void testcreate() {
		String result = "";
		System.out.println("testcreate :");
		try {

			result = multiChainCommand.getStreamCommand().create(streamName);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == null || "".equals(result)) {
			System.err.println("testcreate - result is empty");
		} else {
			System.out.println("Result :");
			System.out.println(result);
		}
	}
	
	private static void testsubscribe() {
		System.out.println("testsubscribe :");
		try {

			multiChainCommand.getStreamCommand().subscribe(streamName);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Result : done !");
	}
	
	private static void testReadWritePermissions(String address) {
		String result = null;
		try {
			result = multiChainCommand.getGrantCommand().grant(address,multiChainCommand.getGrantCommand().SEND | multiChainCommand.getGrantCommand().RECEIVE);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result == null || result == "") {
			System.err.println("testlistPermissions (WALLET) - result list is empty");
		} else {
			System.out.println("testlistPermissions (WALLET) - Result :");
			
		}

	}
	
	private static void testWritePermissions(String address) {
		String result = null;
		try {
			result = multiChainCommand.getGrantCommand().grantWrite(address,streamName);
		} catch (MultichainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result == null || result == "") {
			System.err.println("testlistPermissions (WALLET) - result list is empty");
		} else {
			System.out.println("testlistPermissions (WALLET) - Result :");
			
		}

	}
	
	

}
