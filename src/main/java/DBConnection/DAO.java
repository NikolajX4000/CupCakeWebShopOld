package DBConnection;

import Data.CupCakePiece;
import Data.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author super
 */
public class DAO {

    private DBConnector conn = new DBConnector();

    /**
     * takes a username and returns the user whit that username
     *
     * @param u username of the desired user
     * @return the user with specified username
     */
    public User getUser(String u) {
        PreparedStatement stmt = null;
        User res = null;
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setString(1, u);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                double balance = rs.getDouble("balance");
                String role = rs.getString("role");
                res = new User(id, username, password, balance, role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }

    /**
     * takes an user id and returns the user with that id
     *
     * @param id id of the desired user
     * @return the user with specified id
     */
    public User getUser(int id) {
        PreparedStatement stmt = null;
        User res = null;
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                double balance = rs.getDouble("balance");
                String role = rs.getString("role");
                res = new User(id, username, password, balance, role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }

    /**
     *
     * @return a list of all the users
     */
    public ArrayList<User> getUsers() {
        PreparedStatement stmt = null;
        ArrayList<User> users = new ArrayList();
        try {
            String sql = "SELECT * FROM users;";
            stmt = conn.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                double balance = rs.getDouble("balance");
                String role = rs.getString("role");
                users.add(new User(id, username, password, balance, role));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return users;
    }

    /**
     * creates a user with role customer
     *
     * @param username the username of the new user
     * @param password1 the password of the new user
     * @param password2 verification of the password
     * @return true if successfully created a new user or false if failed to
     * create a new user
     */
    public boolean createCustomer(String username, String password1, String password2) {
        PreparedStatement stmt = null;
        boolean createdCustomer = password1.equals(password2);
        if (createdCustomer) {
            try {
                String sql = "INSERT INTO users (username, password) VALUES (?,?);";
                stmt = conn.getConnection().prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password1);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return createdCustomer;
    }

    /**
     * creates a user with role admin
     *
     * @param username the username of the new user
     * @param password1 the password of the new user
     * @param password2 verification of the password
     * @return true if successfully created a new user or false if failed to
     * create a new user
     */
    public boolean createAdmin(String username, String password1, String password2) {
        PreparedStatement stmt = null;
        boolean createdAdmin = password1.equals(password2);
        if (createdAdmin) {
            try {
                String sql = "INSERT INTO users (username, password, role) VALUES (?,?, 'Admin');";
                stmt = conn.getConnection().prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password1);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return createdAdmin;
    }

    /**
     *
     * @return a list of all toppings
     */
    public ArrayList<CupCakePiece> getToppings() {
        ArrayList<CupCakePiece> toppings = new ArrayList();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM toppings;";
            stmt = conn.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String flavor = rs.getString("topping");
                double price = rs.getDouble("price");
                toppings.add(new CupCakePiece(id, flavor, price));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return toppings;
    }

    /**
     *
     * @return a list of all bottoms
     */
    public ArrayList<CupCakePiece> getBottoms() {
        ArrayList<CupCakePiece> bottoms = new ArrayList();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM bottoms;";
            stmt = conn.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String flavor = rs.getString("bottom");
                double price = rs.getDouble("price");
                bottoms.add(new CupCakePiece(id, flavor, price));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bottoms;
    }

    /**
     * takes a topping id and returns the topping with that id
     *
     * @param id id of the desired topping
     * @return the topping with specified id
     */
    public CupCakePiece getTopping(int id) {
        String topping = "";
        double price = 0.0;
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM toppings where id = ?;";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (!rs.first()) {
                return null;
            }
            topping = rs.getString("topping");
            price = rs.getDouble("price");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return new CupCakePiece(id, topping, price);
    }

    /**
     * takes a bottom id and returns the bottom with that id
     *
     * @param id id of the desired bottom
     * @return the bottom with specified id
     */
    public CupCakePiece getBottom(int id) {
        String bottom = "";
        double price = 0.0;
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM bottoms where id = ?;";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (!rs.first()) {
                return null;
            }
            bottom = rs.getString("bottom");
            price = rs.getDouble("price");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return new CupCakePiece(id, bottom, price);
    }

    /**
     * logging with user credentials
     *
     * @param username the username of the user trying to sign in
     * @param password the password of the user trying to sign in
     * @return the user with the specified credentials
     */
    public User login(String username, String password) {
        PreparedStatement stmt = null;
        User user = null;
        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ? ;";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("role"), rs.getDouble("balance"), rs.getString("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return user;
    }

    /**
     * takes an order id and returns the order with that id
     *
     * @param id the id of the desired order
     * @return the order with specified id
     */
    public Order getOrder(int id) {
        ArrayList<OrderLine> orderlines = new ArrayList();
        PreparedStatement stmt = null;
        Order order = null;
        String dateTime = null;
        try {
            String sql = "SELECT * "
                    + "FROM orderline "
                    + "INNER JOIN orders "
                    + "ON orders.id=orderline.order_id "
                    + "INNER JOIN toppings "
                    + "ON toppings.id=orderline.topping_id "
                    + "INNER JOIN bottoms "
                    + "ON bottoms.id=orderline.bottom_id "
                    + "INNER JOIN users "
                    + "ON users.user_id=orders.user_id "
                    + "WHERE order_id = ?;";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int orderId = rs.getInt("order_id");
                int orderlineId = rs.getInt("orderline.id");
                CupCakePiece topping = new CupCakePiece(rs.getInt("toppings.id"), rs.getString("topping"), rs.getDouble("toppings.price"));
                CupCakePiece bottom = new CupCakePiece(rs.getInt("bottoms.id"), rs.getString("bottom"), rs.getDouble("bottoms.price"));
                double price = rs.getDouble("orderline.price");
                int amount = rs.getInt("amount");
                dateTime = rs.getString("orders.date");
                orderlines.add(new OrderLine(userId, orderId, orderlineId, topping, bottom, price, amount));
            }
            order = new Order(id, orderlines, dateTime);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return order;
    }

    /**
     * takes an user id and returns a list with that users orders
     *
     * @param id the id of the user
     * @return a list with specified users orders
     */
    public ArrayList<Order> getUsersOrders(int id) {
        ArrayList<Order> orders = new ArrayList();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * "
                    + "FROM orders "
                    + "WHERE user_id = ?;";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                String dateTime = rs.getString("date");
                orders.add(new Order(orderId, getOrder(orderId).getOrder(), dateTime));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return orders;
    }

    /**
     *
     * @return a list of all orders
     */
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * "
                    + "FROM orders;";
            stmt = conn.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                String dateTime = rs.getString("date");
                orders.add(new Order(orderId, getOrder(orderId).getOrder(), dateTime));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return orders;
    }

    /**
     * creates a new order and the orders orderlines
     *
     * @param cart a list of all the orders orderlines
     * @param user the user that made the order
     * @return the id of the new order
     */
    public int insertOrder(ArrayList<CupCake> cart, User user) {
        PreparedStatement stmt = null;
        int lastId = 0;
        try {
            String sql = "insert into orders (user_id) values (?)";
            stmt = conn.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.first()) {
                lastId = rs.getInt(1);
            }
            sql = "insert into orderline (topping_id, bottom_id, order_id, price, amount) values (?, ?, ?, ?, ?)";
            stmt = conn.getConnection().prepareStatement(sql);
            for (CupCake c : cart) {
                stmt.setInt(1, c.getTopping().getId());
                stmt.setInt(2, c.getBottom().getId());
                stmt.setInt(3, lastId);
                stmt.setDouble(4, c.getPrice());
                stmt.setInt(5, c.getAmount());
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lastId;
    }

    /**
     * adds a new topping with specified flavor and price
     *
     * @param flavor the flavor of the new topping
     * @param price the price of the new topping
     * @return true if successfully created a new topping or false if failed to
     * create new topping
     */
    public boolean addTopping(String flavor, double price) {
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            String sql = "INSERT INTO toppings (topping, price) VALUES (?, ?);";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setString(1, flavor);
            stmt.setDouble(2, price);
            success = stmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return success;
    }

    /**
     * adds a new bottom with specified flavor and price
     *
     * @param flavor the flavor of the new bottom
     * @param price the price of the new bottom
     * @return true if successfully created a new bottom or false if failed to
     * create new bottom
     */
    public boolean addBottom(String flavor, double price) {
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            String sql = "INSERT INTO bottoms (bottom, price) VALUES (?, ?);";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setString(1, flavor);
            stmt.setDouble(2, price);
            success = stmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return success;
    }

    /**
     * changes the amout on the orderline with specified id to the specified
     * amount
     *
     * @param id the id of the desired orderline
     * @param amount the desired amount
     * @return true if successfully updated or false if failed to update
     */
    public boolean changeOrderlineAmount(int id, int amount) {
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            String sql = "UPDATE orderline SET amount= ? WHERE id= ?;";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setInt(1, amount);
            stmt.setInt(2, id);
            success = stmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return success;
    }

    /**
     * takes an user id and returns the username of that user
     *
     * @param id the id of the desired user
     * @return the username of the desired user
     */
    public String getUserById(int id) {
        PreparedStatement stmt = null;
        String username = null;
        try {
            String sql = "SELECT * FROM users WHERE user_id= ?;";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeQuery();

            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                username = rs.getString("username");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return username;
    }

    /**
     * add the specified amount to the balance of the user with specified id
     *
     * @param id the id of the desired user
     * @param value the value to be added to the balance
     * @return the new balance value
     */
    public double deposit(int id, double value) {
        PreparedStatement stmt = null;
        double balance = 0;
        try {
            String sql = "UPDATE users SET balance=? WHERE user_id= ?;";
            stmt = conn.getConnection().prepareStatement(sql);
            balance = getUser(id).getBalance() + value;
            stmt.setDouble(1, balance);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return balance;
    }

    /**
     * subtract the specified amount from the balance of the user with specified
     * id
     *
     * @param id the id of the desired user
     * @param value the value to be subtracted from the balance
     * @return the new balance value
     */
    public boolean withdrawal(int id, double value) {
        PreparedStatement stmt = null;
        double balance = getUser(id).getBalance();
        boolean succes = false;
        if (balance - value < 0) {
            return false;
        }
        try {
            String sql = "UPDATE users SET balance=? WHERE user_id= ?;";
            stmt = conn.getConnection().prepareStatement(sql);
            balance = getUser(id).getBalance() - value;
            stmt.setDouble(1, balance);
            stmt.setInt(2, id);
            succes = stmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return succes;
    }

    /**
     * changes the amout on the orderline with specified id to the specified
     * amount
     *
     * @param orderlineId the id of the desired orderline
     * @param amount the desired amount
     */
    public void updateOrderLine(int orderlineId, int amount) {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE `CupCakeWebShop`.`orderline` SET `amount`=? WHERE `id`=?;";
            stmt = conn.getConnection().prepareStatement(sql);
            stmt.setInt(1, amount);
            stmt.setInt(2, orderlineId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
