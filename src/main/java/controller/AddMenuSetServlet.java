package controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MenuSet;
import model.RestaurantOwner;

public class AddMenuSetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToMenuSetServlet";
//        MultipartRequest request = new MultipartRequest(request, "C:\\Users\\USER\\Documents\\NetBeansProjects\\OEB\\src\\main\\webapp\\images", "UTF-8");
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        String menuSetNameTH = request.getParameter("menuSetNameTH");
        String menuSetNameEN = request.getParameter("menuSetNameEN");
        String menuDesc = request.getParameter("menuDesc");
//        String menuPicPath = "images/" + request.getFilesystemName("addImgPicPath");
//        if (request.getFilesystemName("addImgPicPath") == null) {
//            menuPicPath = null;
//        }
        String price = request.getParameter("price");
        String isOfficialMenuSet = request.getParameter("isOfficialMenuSet");
        String isAvailable = request.getParameter("isAvailable");
        String chooseMenu[] = request.getParameterValues("table_records");
        String amount[] = request.getParameterValues("amount");
        MenuSet ms = new MenuSet();
        ms.setMenuSetNameTH(menuSetNameTH);
        ms.setMenuSetNameEN(menuSetNameEN);
        ms.setMenuSetDesc(menuDesc);
//        ms.setMenuSetPicPath(menuPicPath);
        ms.setMenuSetPrice(Double.parseDouble(price));
        if (isOfficialMenuSet != null) {
            ms.setIsOfficialMenuSet(1);
        } else {
            ms.setIsOfficialMenuSet(0);
        }
        if (isAvailable != null) {
            ms.setIsAvailable(true);
        } else {
            ms.setIsAvailable(false);
        }
        ms.setBranchNo(ro.getBranchNo());
        Map<Integer, Integer> menus = new LinkedHashMap<Integer, Integer>();
        for (int i = 0; i < chooseMenu.length; i++) {
            menus.put(Integer.parseInt(chooseMenu[i]), Integer.parseInt(amount[i]));
        }
        ms.addMenuSet(menus, 0);
        response.sendRedirect(target);
        return;
    }
}
