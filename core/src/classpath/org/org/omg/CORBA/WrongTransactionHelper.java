/* WrongTransactionHelper.java --
   Copyright (C) 2005 Free Software Foundation, Inc.

This file is part of GNU Classpath.

GNU Classpath is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

GNU Classpath is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with GNU Classpath; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

Linking this library statically or dynamically with other modules is
making a combined work based on this library.  Thus, the terms and
conditions of the GNU General Public License cover the whole
combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent
modules, and to copy and distribute the resulting executable under
terms of your choice, provided that you also meet, for each linked
independent module, the terms and conditions of the license of that
module.  An independent module is a module which is not derived from
or based on this library.  If you modify this library, you may extend
this exception to your version of the library, but you are not
obligated to do so.  If you do not wish to do so, delete this
exception statement from your version. */


package org.omg.CORBA;

import gnu.CORBA.EmptyExceptionHolder;
import gnu.CORBA.Minor;
import gnu.CORBA.OrbRestricted;

import org.omg.CORBA.Any;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.ORB;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

/**
* The helper operations for the exception {@link WrongTransaction}.
*
* @author Audrius Meskauskas, Lithuania (AudriusA@Bioinformatics.org)
*/
public abstract class WrongTransactionHelper
{
  /**
   * Create the WrongTransaction typecode (structure,
   * named "WrongTransaction"), no fields.
   */
  public static TypeCode type()
  {
    ORB orb = OrbRestricted.Singleton;
        StructMember[] members = new StructMember[ 0 ];
    return orb.create_exception_tc(id(), "WrongTransaction", members);
  }

  /* Every user exception with no user defined
     fields can use EmptyExceptionHolder */

  /**
   * Insert the WrongTransaction into the given Any.
   *
   * @param any the Any to insert into.
   * @param that the WrongTransaction to insert.
   */
  public static void insert(Any any, WrongTransaction that)
  {
    any.insert_Streamable(new EmptyExceptionHolder(that, type()));
  }

  /**
   * Extract the WrongTransaction from given Any.
   *
   * @throws BAD_OPERATION if the passed Any does not contain WrongTransaction.
   */
  public static WrongTransaction extract(Any any)
  {
    try
      {
        EmptyExceptionHolder h =
          (EmptyExceptionHolder) any.extract_Streamable();
        return (WrongTransaction) h.value;
      }
    catch (ClassCastException cex)
      {
        BAD_OPERATION bad = new BAD_OPERATION("WrongTransaction expected");
        bad.minor = Minor.Any;
        bad.initCause(cex);
        throw bad;
      }
  }

  /**
   * Get the WrongTransaction repository id.
   *
   * @return "IDL:omg.org/CORBA/WrongTransaction:1.0", always.
   */
  public static String id()
  {
    return "IDL:omg.org/CORBA/WrongTransaction:1.0";
  }

  /**
   * Read the exception from the CDR intput stream.
   *
   * @param input a org.omg.CORBA.portable stream to read from.
   */
  public static WrongTransaction read(InputStream input)
  {
    // Read the exception repository id.
    String id = input.read_string();
    WrongTransaction value = new WrongTransaction(id);

    return value;
  }

  /**
   * Write the exception to the CDR output stream.
   *
   * @param output a org.omg.CORBA.portable stream stream to write into.
   * @param value a value to write.
   */
  public static void write(OutputStream output, WrongTransaction value)
  {
    // Write the exception repository id.
    output.write_string(id());
  }
}