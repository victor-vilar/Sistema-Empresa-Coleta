import { NgModule } from "@angular/core";
import { MockTestComponent } from "./mock-test.component";
import { MockPipe } from "./mock-pipe.pipe";

@NgModule({
    declarations: [
        MockTestComponent,
        MockPipe,
    ],
    exports:[
        MockTestComponent,
        MockPipe
    ]
})
export class TestModule{}