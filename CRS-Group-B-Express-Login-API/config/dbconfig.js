class DbConnection {
    constructor() {
        this.user = 'root';
        this.password = "root";
        this.db = "crsdatabase";
        this.host = "localhost"
        this.port = 3001
    }
    getUserName() {
        return this.user
    }
    getPassword() {
        return this.password
    }
    getHostName() {
        return this.host
    }
    getDataBaseName() {
        return this.db
    }
    getPort(){
        return this.port;
    }
}
module.exports = DbConnection;