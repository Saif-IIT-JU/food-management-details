<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Author: saifuzzaman
--%>

<hr>
<div class="container-fluid">
    <div class="row">
        <div class="col-xl-6 bg-warning">
            <h4><fmt:message key="contact"/></h4>
            <fmt:message key="address.line1"/>
            <br>
            <fmt:message key="address.line2"/>
            <br>
            <fmt:message key="address.line3"/>
            <br>
            <fmt:message key="address.line4"/>
            <br>

            <a href="mailto:info@therapbd.com"><fmt:message key="email.address"/></a><br>
        </div>

        <div class="col-xl-6 bg-warning">
            <h4><fmt:message key="location"/></h4>
            <div class="map-responsive">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d98198.59361361229!2d90.25343167383372!3d23.842516935671814!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3755e998af65bee5%3A0x51e41cefc20b8fa8!2sJahangirnagar%20University%2C%20Savar%20Union!5e0!3m2!1sen!2sbd!4v1668438412706!5m2!1sen!2sbd" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"
                        style="border:0;"
                        allowfullscreen=""
                        loading="lazy"
                        referrerpolicy="no-referrer-when-downgrade">>
                </iframe>
            </div>
        </div>
    </div>
</div>
