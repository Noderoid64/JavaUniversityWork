import { Component, OnInit } from '@angular/core';
import { RestWebService } from '../services/rest-web.service';
import { Motorcycle } from '../model/motorcycle.model';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'moto-table-root',
    templateUrl: './motorcycle-table.component.html',
    styleUrls: ['./motorcycle-table.component.scss']
})
export class MotorcycleTableComponent implements OnInit {

    public dataSource: Motorcycle[];
    public filteredDataSource: Motorcycle[];
    public displayedColumns: string[] = ['title', 'type', 'price', 'year', 'engineSize', 'mileage'];

    public types: Map<number, string>;

    public type: string;
    public title: string;
    public priceFrom: number;
    public priceTo: number;
    public dateFrom: Date;
    public dateTo: Date;
    public engineSizeFrom: number;
    public engineSizeTo: number;
    public mileageFrom: number;
    public mileageTo: number;

    public titleControl: FormControl = new FormControl();
    public typeControl: FormControl = new FormControl();
    public priceFromControl: FormControl = new FormControl();
    public priceToControl: FormControl = new FormControl();
    public dateFromControl: FormControl = new FormControl();
    public dateToControl: FormControl = new FormControl();
    public engineSizeFromControl: FormControl = new FormControl();
    public engineSizeToControl: FormControl = new FormControl();
    public mileageFromControl: FormControl = new FormControl();
    public mileageToControl: FormControl = new FormControl();

    public newTitleControl: FormControl = new FormControl();
    public newTypeControl: FormControl = new FormControl();
    public newPriceControl: FormControl = new FormControl();
    public newDateControl: FormControl = new FormControl();
    public newEngineSizeControl: FormControl = new FormControl();
    public newMileageControl: FormControl = new FormControl();

    constructor(private restWebService: RestWebService) {
        this.titleControl.valueChanges.subscribe(() => this.applyFilter());
        this.typeControl.valueChanges.subscribe(() => this.applyFilter());
        this.priceFromControl.valueChanges.subscribe(() => this.applyFilter());
        this.priceToControl.valueChanges.subscribe(() => this.applyFilter());
        this.dateFromControl.valueChanges.subscribe(() => this.applyFilter());
        this.dateToControl.valueChanges.subscribe(() => this.applyFilter());
        this.engineSizeFromControl.valueChanges.subscribe(() => this.applyFilter());
        this.engineSizeToControl.valueChanges.subscribe(() => this.applyFilter());
        this.mileageFromControl.valueChanges.subscribe(() => this.applyFilter());
        this.mileageToControl.valueChanges.subscribe(() => this.applyFilter());
    }

    public ngOnInit() {
        this.restWebService.getAllMotorcycles().subscribe(val => {
            this.dataSource = val;
            this.applyFilter();
            console.log(this.dataSource);
        });
        this.restWebService.getTypes().subscribe(val => {
            this.types = val;
            console.log(this.types);
        });
    }

    // public titleChange(event: any): void {
    //     console.log(event);
    // }

    public applyFilter(): void {
        this.filteredDataSource = this.dataSource;
        if (this.titleControl.value && this.filteredDataSource)
            this.filteredDataSource = this.filteredDataSource.filter(val => val.title.toLowerCase().startsWith(this.titleControl.value.toLowerCase()));
        if (this.typeControl.value && this.filteredDataSource)
            this.filteredDataSource = this.filteredDataSource.filter(val => val.type === this.typeControl.value);
        if (this.priceFromControl.value && this.filteredDataSource)
            this.filteredDataSource = this.filteredDataSource.filter(val => val.price >= this.priceFromControl.value);
        if (this.priceToControl.value && this.filteredDataSource)
            this.filteredDataSource = this.filteredDataSource.filter(val => val.price <= this.priceToControl.value);
        if (this.dateFromControl.value && this.filteredDataSource)
            this.filteredDataSource = this.filteredDataSource.filter(val => new Date(val.date).getTime() >= this.dateFromControl.value.getTime());
        if (this.dateToControl.value && this.filteredDataSource)
            this.filteredDataSource = this.filteredDataSource.filter(val => new Date(val.date).getTime() <= this.dateToControl.value.getTime());
        if (this.engineSizeFromControl.value && this.filteredDataSource)
            this.filteredDataSource = this.filteredDataSource.filter(val => val.engineSize >= this.engineSizeFromControl.value);
        if (this.engineSizeToControl.value && this.filteredDataSource)
            this.filteredDataSource = this.filteredDataSource.filter(val => val.engineSize <= this.engineSizeToControl.value);
        if (this.mileageFromControl.value && this.filteredDataSource)
            this.filteredDataSource = this.filteredDataSource.filter(val => val.mileage >= this.mileageFromControl.value);
        if (this.mileageToControl.value && this.filteredDataSource)
            this.filteredDataSource = this.filteredDataSource.filter(val => val.mileage <= this.mileageToControl.value);
    }

    public addNewItem(): void {
        console.log('hell')
        if (this.newDateControl.value &&
            this.newEngineSizeControl.value &&
            this.newMileageControl.value &&
            this.newPriceControl.value &&
            this.newTitleControl.value &&
            this.newTypeControl.value) {
                this.restWebService.addNewRecord(
                    this.newTitleControl.value,
                    0,
                    this.newPriceControl.value,
                    this.newDateControl.value,
                    this.newEngineSizeControl.value,
                    this.newMileageControl.value
                ).subscribe(val => {
                    this.ngOnInit();
                })
        } else {
            console.log('fill the form');
            console.log(this.newDateControl.value);
            console.log(this.newEngineSizeControl.value);
            console.log(this.newMileageControl.value);
                console.log(this.newPriceControl.value);
                console.log(this.newTitleControl.value);
                console.log(this.newTypeControl.value);
        }
    }
}
