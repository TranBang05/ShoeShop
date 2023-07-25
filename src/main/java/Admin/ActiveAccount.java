package Admin;



import Model.Staff;
import Model.User;
import OTPFunction.MailSending;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ActiveAccountController", value = "/activeAccount")
public class ActiveAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();


        User acc = (User) session.getAttribute("username");
        Staff accStaff = (Staff) session.getAttribute("staff");
        if (acc != null) {

            MailSending mail = new MailSending();
            String opt = mail.generateOtp();
            session.setAttribute("optValue", opt);
            Thread thread = new Thread() {
                @Override
                public void run() {
                    mail.authenEmail("tranxuanbang.yt@gmail.com", "zptgaabhvfqmsvnw", acc.getEmail(), opt);
                }
            };

            thread.start();
            request.setAttribute("email", acc.getEmail());
            request.getRequestDispatcher("ActiveAccount.jsp").forward(request, response);
        } else if (accStaff != null) {
            MailSending mail = new MailSending();
            String opt = mail.generateOtp();
            session.setAttribute("optValue", opt);
            Thread thread = new Thread() {
                @Override
                public void run() {
                    mail.authenEmailStaff("tranxuanbang.yt@gmail.com", "zptgaabhvfqmsvnw", accStaff.getEmail(), accStaff.getPassword (), opt);
                }
            };
            thread.start();
            request.setAttribute("email", accStaff.getEmail());
            request.getRequestDispatcher("ActiveAccount.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String optValue = (String) session.getAttribute("optValue");


        String optInput = request.getParameter("otp");

        String email = request.getParameter("email");
        User acc = (User) session.getAttribute("username");
        Staff accStaff = (Staff) session.getAttribute("staff");
        String option = request.getParameter("option");


        if (option.equals("active")) {

            if (optInput.equals(optValue)) {
                System.out.println (optInput);
                if (acc != null) {

                    acc.setStatus(1);
                    User daoC = new User ();
                    daoC.updateUserByPre (acc);
                    session.setAttribute("username", acc);


                } else if (accStaff != null) {
                    System.out.println (accStaff.getEmail ());
                    accStaff.setRole (1);
                    Staff daoS = new Staff();
                    daoS.updateStaffByPre(accStaff);

                    session.setAttribute("staff", accStaff);

                }



                response.sendRedirect("home");


            } else {


                String mess = "Sai mã OTP, Xin hãy nhập lại";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("ActiveAccount.jsp").forward(request, response);

            }

        }

        else if (option.equals("sendAgain")) {
            // Gửi lại email khi người dùng ấn nút "Gửi lại mã OTP"
            session.removeAttribute("optValue");
            MailSending mail = new MailSending();
            String newOptValue = mail.generateOtp();
            session.setAttribute("optValue", newOptValue);
            Thread thread = new Thread() {
                @Override
                public void run() {
                    if (acc != null) {
                        mail.authenEmail("tranxuanbang.yt@gmail.com", "zptgaabhvfqmsvnw", acc.getEmail(), newOptValue);
                    } else if (accStaff != null) {
                        mail.authenEmailStaff("tranxuanbang.yt@gmail.com", "zptgaabhvfqmsvnw", accStaff.getEmail(), accStaff.getPassword (), newOptValue);
                    }
                }
            };
            thread.start();
            request.getRequestDispatcher("ActiveAccount.jsp").forward(request, response);
        }else{


            doGet(request, response);
        }
    }
}

