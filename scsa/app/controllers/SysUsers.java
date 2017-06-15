package controllers;
import play.*;
import play.mvc.With;

@With(Secure.class)
@Check("administrator") 
public class SysUsers  extends CRUD {

}
