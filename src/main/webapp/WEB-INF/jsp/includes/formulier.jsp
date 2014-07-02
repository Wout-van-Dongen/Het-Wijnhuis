<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section id="bestellingsformulier">

    <c:url var="bevestigURL" value="/wijnen/bevestiging.html"/>

    <form method="post" action="${bevestigURL}">
        <div class="input"><label>Naam:</label>
            <input type="text" name="naam" 
                   <c:if test="${not empty naam}">value="${naam}"</c:if>/>
            <span class="fouten"><c:if test="${not empty naamFout}">${naamFout}</c:if></span>
        </div>

        <div class="input"><label>Straat:</label>
            <input type="text" name="straat" 
                    <c:if test="${not empty straat}">value="${straat}"</c:if>/>
        <span class="fouten"><c:if test="${not empty straatFout}">${straatFout}</c:if></span>
        </div>

        <div class="input"><label>Huisnr:</label>
            <input type="text"  name="huisnr"
                    <c:if test="${not empty huisnr}">value="${huisnr}"</c:if>/>
        <span class="fouten"><c:if test="${not empty huisnrFout}">${huisnrFout}</c:if></span>
        </div>

        <div class="input"><label>Postcode:</label>
            <input type="text" name="postcode"
                    <c:if test="${not empty postcode}">value="${postcode}"</c:if>/>
        <span class="fouten"><c:if test="${not empty postcodeFout}">${postcodeFout}</c:if></span>
        </div>

        <div class="input"><label>Gemeente:</label>
            <input type="text" name="gemeente"
                    <c:if test="${not empty gemeente}">value="${gemeente}"</c:if>/>
        <span class="fouten"><c:if test="${not empty gemeenteFout}">${gemeenteFout}</c:if></span>
        </div>

        <div class="input">
            <input type="radio" name="levering" value="afhalen" checked/>
            <label>Afhalen</label>
        </div>
        <div class="input">
            <input type="radio" name="levering" value="opsturen"/>
            <label>Opsturen</label>
        </div>

        <div class="input">
            <input type="submit" value="Als bestelbon bevestigen"/>
        </div>


    </form>

</section>