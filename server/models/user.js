var mongoose = require("mongoose");

var UserSchema = new mongoose.Schema({
    username: String,
    email: String,
    walletAddress: String,
});


module.exports = mongoose.model("User", UserSchema);