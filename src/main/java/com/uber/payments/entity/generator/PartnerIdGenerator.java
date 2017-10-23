package com.uber.payments.entity.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * An id generator to generate ids of partners. Usually it is of the scheme PPAUG0900001
 * PP + MON + DT + 00011
 */
public class PartnerIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object o) throws HibernateException {

        GregorianCalendar calendar = new GregorianCalendar();
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()).toUpperCase();
        String date = String.format("%02d", calendar.get(Calendar.DATE));
        String prefix = "PP" + month + date;
        Connection connection = session.connection();

        try {
            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select count(Id) as count from Partner");

            if(rs.next())
            {
                int count = rs.getInt(1);
                int nextId = count + 1;
                String generatedId = null;
                if(count != 0) {
                    generatedId = prefix + String.format("%05d", nextId);
                }

                return generatedId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
