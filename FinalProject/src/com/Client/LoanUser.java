package com.Client;

import java.util.Scanner;

import com.Beans.UserDetails;
import com.DAOServiceLayer.DaoLoanInterface;
import com.DAOServiceLayer.DaoLoginInterface;
import com.Exceptions.ExceedPeriodException;
import com.Providers.DaoLoanInstanceProvider;
import com.Providers.LoanInstanceProvider;
import com.Providers.LoginInstanceProvider;
import com.ServiceLayer.LoanServiceInterface;

public class LoanUser 
{

	private static DaoLoanInterface dLoan;

	public static void main(String[] args) 
	{
		int choice;
		String userName;
		String Password;
		UserDetails User;
		boolean check;
		DaoLoginInterface da=LoginInstanceProvider.getInstance();
		LoanServiceInterface Loan = null;
		dLoan = null;
		try {
		do
		{
			System.out.println("****Welcome to Home Loan System****");
			System.out.println("1.New User?");
			System.out.println("2.Login");
			System.out.println("3.Exit");
			
			
			Scanner sc=new Scanner(System.in);
			
			choice=sc.nextInt();
			switch (choice) 
			{
			case 1:
				Double amount;
					int duration;
					String Name;
					
					System.out.println("Enter your first name");
					Name=sc.next();
					System.out.println("Enter your last name");
					Name+=sc.next();
					check=true;
					do {
						System.out.println("Enter Username");
						userName=sc.next();
						check=da.verifyUserName(userName);
						if(check==true) 
						{
							System.out.println("User Name Already exist please try again!!!");
						}
					}while(check);
					
					
					System.out.println("Enter Password");
					Password=sc.next();
					System.out.println("Enter loan amount");
					amount=sc.nextDouble();
					System.out.println("Enter duration of loan period(in months)");
					duration=sc.nextInt();
					if(duration >50)
					{
						throw new ExceedPeriodException("Too Long duration. Period should be less than or equal to 50");
					}
					User=new UserDetails(userName, Password);
					da.createUser(User);
					Loan=LoanInstanceProvider.getInstance("HomeLoan");
					if(Loan.registerLoanDetails(amount, duration,User))
					{
						System.out.println("Successfully registered please login to check details");
						
					}
					else {
						System.out.println("failed");
					}
					break;
			case 2:
					check=true;
					do {
						System.out.println("Enter Username");
						userName=sc.next();
						check=da.verifyUserName(userName);
						if(check==true) 
						{
							check=false;
						}
						else
						{
							System.out.println("Not a valid username");
							check=true;
						}
							
					}while(check);
					check=true;
					do {
						System.out.println("Enter Password");
						Password=sc.next();
						User=new UserDetails(userName, Password);
						
						if(da.validate(User))
						{
							check=false;
							System.out.println("Login successfull!!");
						}
						else
						{
							System.out.println("Wrong password.. Please try again!");
						}
							
					}while(check);
					
						
					int choice2;
					
					do {
						System.out.println("Loan Type");
						System.out.println("1. Home Loan");
						System.out.println("2. Car Loan");
						System.out.println("3. Exit");
						choice2=sc.nextInt();
						switch (choice2) 
						{
						case 1:
							
								Loan=LoanInstanceProvider.getInstance("HomeLoan");
								if(Loan.findLoan("Home", User.getAccountNumber()))
								{
									System.out.println("Welcome Home LoanHolder");
									choice2=3;
								}
								else
								{
									System.out.println("Please verify loan type");
								}
								
								break;
						case 2:
								Loan=LoanInstanceProvider.getInstance("CarLoan");
								if(Loan.findLoan("Car", User.getAccountNumber()))
								{
									System.out.println("Welcome Car LoanHolder");
									choice2=3;
								}
								else
								{
									System.out.println("Please verify loan type");
								}
								break;
						case 3:
								choice2=33;
							break;
						default:
								System.out.println("Not a valid choice!Try again!!");
								break;
						}
						
					}while(choice2!=3 && choice2!=33);
					
					if(choice2==3)
					{
						do 
						{
							System.out.println("Welcome");
							
							System.out.println("1. Loan Details");
							System.out.println("2. Pay Monthly Installment");
							System.out.println("3. Pay Partial LoanAmount");
							System.out.println("4. Pay Full Remaining");
							System.out.println("5. EMI Rate");
							System.out.println("6. Transactions History");
							System.out.println("7. Exit");
							choice2=sc.nextInt();
							Double amt;
							switch (choice2) {
							case 1:
									Loan.loanDetails();
									break;
							case 2:
									System.out.println("Enter amount to be paid");
									
									if(Loan.payInstallment())
									{
										System.out.println("Successfully paid amount..");
										System.out.println("Thank You!");
									}
									else
									{
										System.out.println("Error!...Please Try again!!");
									}
									break;
							case 3:
									System.out.println("Enter amount to be paid");
									amt=sc.nextDouble();
									int choice3;
									sc=new Scanner(System.in);
									
									do {
										System.out.println("Mode of Partial Installment");
										System.out.println("1. Reduce no installment");
										System.out.println("2. Reduce monthly installment amount");
										System.out.println("3. Exit");
										choice3=sc.nextInt();
										switch (choice3) 
										{
										case 1:
												System.out.println("Enter new period of return");
												int per=sc.nextInt();
												if(Loan.payPartialInstallment(amt,per))
												{
													
													System.out.println("Successfully paid amount..");
													System.out.println("Thank You!");
												}
												else
												{
													System.out.println("Error!...Please Try again!!");
												}
												break;
										case 2:
												if(Loan.payPartialInstallment(amt))
												{
													
													System.out.println("Successfully paid amount..");
													System.out.println("Thank You!");
												}
												else
												{
													System.out.println("Error!...Please Try again!!");
												}
												break;
										
										default:
												System.out.println("Not a valid choice!Try again!!");
												break;
										}
										
									}while(choice3!=3);								
									
									break;
							case 4:
									System.out.println("Enter amount to be paid");
									
									if(Loan.payFullInstallment())
									{
										System.out.println("Successfully paid amount..");
										System.out.println("Thank You!");
									}
									else
									{
										System.out.println("Error!...Please Try again!!");
									}
										break;
							case 5:
									double Emi=Loan.emiRate()*100;
									assert Emi>0.0:"Not a valid EmI Value";
									System.out.println("Current EMI Rate is"+Emi+"%");
									break;
							case 6:
									Loan.transactionHistory();
									break;
							case 7:
									dLoan=DaoLoanInstanceProvider.getInstnace();
									if(dLoan.updateLoanDetails(Loan.getHL()))
									{
										System.out.println("Updated");
									}
									else
									{
										
									}
								    break;
							default:
								break;
							}
								
								
						}while(choice2!=7);
						
					}
					
					break;
			default:
					break;
			}
		}while(choice!=3);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
	}

}
