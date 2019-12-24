//window.user.setUser("aaaa", "bbb", "123", "123" ,"123" ,"123");
let user = JSON.parse(window.user.getUser());
console.log(user)
let flag = true;
if(user.id === undefined || user.id === undefined === null) {
    flag = false;
}

new Vue({
    el: "#home",
    data: {
        isPrepared: false,
        activities: null
    },
    mounted: function () {
        let that = this;
        axios
            .get("http://www.llworm.cn:8080/activities/intro")
            .then(response => {
                // console.log(response);
                that.activities = response.data;
                that.isPrepared = true;
            })
            .catch(error => {
                console.log(error);
            });
    },
    methods: {
        navigate: function (t) {
            window.location.href = "activity.html?id=" + t;
        }
    }
});

let activity = new Vue({
    el: "#activity",
    data: {
        title: "未知",
        page: "找不到了喵"
    },
    mounted: function () {
        let str = window.location.href;
        let num = str.indexOf("?");
        str = str.substr(num + 1);
        let arr = str.split('&');
        num = arr[0].indexOf('=');
        id = arr[0].substr(num + 1);
        axios
            .get("http://www.llworm.cn:8080/activities/info?id=" + id)
            .then(response => {
                // console.log(response);
                this.title = response.data.title
                this.page = response.data.content
            })
            .catch(error => {
                console.log(error);
            });
    }
});

new Vue({
    el: "#bookshelf",
    data: {
        books: null
    },
    mounted: function () {
        let that = this;
        axios
            .get("http://www.llworm.cn:8080/books/info")
            .then(response => {
                // console.log(response);
                that.books = response.data
            })
            .catch(error => {
                console.log(error);
            });
    },
    methods: {
        borrow: function (id) {
            if(flag) {
                let that = this;
                let stuNum = user.stuNum;
                axios
                    .get("http://www.llworm.cn:8080/books/borrow?id=" + id + "&stuNum=" + stuNum)
                    .then(response => {
                    // console.log(response);
                        window.location.href = "bookshelf.html"
                    })
                    .catch(error => {
                        console.log(error);
                     });
            } else {
                window.location.href = "login.html?type=2";
            }
        }
    }
});

new Vue({
    el: "#account",
    data: {
        accounts: null,
    },
    mounted: function () {
        let that = this;
        axios
            .get("http://www.llworm.cn:8080/accounts/part")
            .then(response => {
                // console.log(response);
                that.accounts = response.data
            })
            .catch(error => {
                console.log(error);
            });
    },
    methods: {

    }
});

new Vue({
    el: "#myBooks",
    data: {
        books: null
    },
    mounted: function () {
        if(flag) {
                    let stuNum = user.stuNum;
                    let that = this;
                    axios
                        .get("http://www.llworm.cn:8080/books/self?stuNum=" + stuNum)
                        .then(response => {
                            // console.log(response);
                            that.books = response.data
                        })
                        .catch(error => {
                            console.log(error);
                        });
        } else {
//            window.location.href = "login.html?type=1"
        }
    },
    methods: {
        returnBook: function (id) {
            let that = this;
            axios
                .get("http://www.llworm.cn:8080/books/return?id=" + id)
                .then(response => {
                    // console.log(response);
                    window.location.href = "books.html"
                })
                .catch(error => {
                    console.log(error);
                });
        }
    }
});

new Vue({
    el: "#login",
    data: {
        stuNum: null,
        pwd: null,
        error: false,
        success: false
    },
    methods: {
        login: function () {
            let that = this;
            axios
                .get("http://www.llworm.cn:8080/users/login?stuNum=" + that.stuNum + "&pwd=" + that.pwd)
                .then(response => {
                    // console.log(response);
                    if (response.data != null && response.data != undefined && response.data != "") {
                        console.log(response.data)
                        window.user.setUser(response.data.id, response.data.name, response.data.stuNum, response.data.qq, response.data.phone, response.data.sex);
                        that.success = true;
                        that.error = false;
//                        window.location.href = "self.html"
                    } else {
                        that.error = true;
                        that.success = false;
                    }

                })
                .catch(error => {
                    that.error = true;
                    that.success = false;
                    console.log(error);
                });
        }
    },
    mounted: function () {
        this.error = false;
        this.success = false;
    }
});

let vm = new Vue({
    el: "#userInfo",
    data: {
        qq: null,
        phone: null,
        success: false,
        error: false,
        name: null,
        stuNum: null,
        sex: null
    },
    mounted: function() {
        if(flag) {
            this.qq = user.qq;
            this.phone = user.phone;
            this.name = user.name;
            this.stuNum = user.stuNum;
            this.sex = user.sex
        } else {
//            window.location.href = "login.html?type=1";
        }
    },
    methods: {
        updateUser: function () {
            let that = this;
            stuNum = user.stuNum
            if (/^1(3|4|5|6|7|8|9)\d{9}$/.test(that.phone) && that.qq != "") {
                axios
                    .get("http://www.llworm.cn:8080/users/updates?stuNum=" + stuNum + "&qq=" + that.qq + "&phone=" + that.phone)
                    .then(response => {
                        // console.log(response);
                        that.success = true;
                        that.error = false;
                        window.user.setUser(that.qq, that.phone);
                    })
                    .catch(error => {
                        console.log(error);
                    });
            } else {
                that.error = true;
                that.success = false;
            }

        }
    }
});