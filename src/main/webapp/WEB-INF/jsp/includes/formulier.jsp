<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section id="bestellingsformulier">

    <c:url var="bevestigURL" value="/wijnen/bevestiging.html"/>

    <form action="bevestigURL">
        <div class="input"><label>Naam:</label>
            <input type="text" name="naam"/></div>

        <div class="input"><label>Straat:</label>
            <input type="text" name="straat"/> </div>

        <div class="input"><label>Huisnr:</label>
            <input type="text"  name="huisnr"/></div>

        <div class="input"><label>Postcode:</label>
            <input type="text" name="postcode"/></div>

        <div class="input"><label>Gemeente:</label>
            <input type="text" name="gemeente"/></div>

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