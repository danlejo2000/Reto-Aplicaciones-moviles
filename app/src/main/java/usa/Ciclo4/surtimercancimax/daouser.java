package usa.Ciclo4.surtimercancimax;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daouser {
    int band=0;
    Context c;
    user u;
    ArrayList<user> lista;
    SQLiteDatabase sql;
    String bd= "BDusuarios";
    String tabla="create table if not exists usuario(id integer primary key autoincrement,usuario text,contra text, nombre text, apellido text)";

    public daouser(Context c)
    {
        this.c= c;
        sql=c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u= new user();
    }

    public boolean insertUser(user u)
    {
        if(buscar(u.getUsuario())==0)
        {
            ContentValues cv= new ContentValues();
            cv.put("usuario", u.getUsuario());
            cv.put("contra", u.getClave());
            cv.put("nombre", u.getNombre());
            cv.put("apellido", u.getApellidos());

            return(sql.insert("usuario",null,cv)>0);
        }else
        {
            return false;
        }
    }
    public int buscar(String u)
    {
        int x=0;
        lista= selectUsuarios();
        for (user us: lista) {
            if(us.getUsuario().equals(u))
            {
                x++;
            }
        }
        return x;
    }

    public ArrayList<user> selectUsuarios() {
        ArrayList<user> lista= new ArrayList<user>();
        lista.clear();
        Cursor cr= sql.rawQuery("select*from usuario",null);

        if(cr!=null&&cr.moveToFirst())
        {
            do{
                user u= new user();
                u.setID(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setClave(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setApellidos(cr.getString(4));
                lista.add(u);
            }while(cr.moveToNext());

        }

        return lista;
    }
    public int login(String u, String p)
    {
        int a=0;
        Cursor cr= sql.rawQuery("select*from usuario",null);

        if(cr!=null&&cr.moveToFirst())
        {
            do{
               if(cr.getString(1).equals(u)&&cr.getString(2).equals(p))
               {
                   a++;
               }
            }while(cr.moveToNext());

        }
        return a;
    }

    public user getusuario(String u, String p)
    {
        lista=selectUsuarios();
        for(user us:lista)
        {
            if(us.getUsuario().equals(u)&&us.getClave().equals(p))
            {
                return us;
            }
        }
        return null;
    }


    public user getusuarioByID(int id)
    {
        lista=selectUsuarios();
        for(user us:lista)
        {
            if(us.getID()==id)
            {
                return us;
            }
        }
        return null;
    }
}
